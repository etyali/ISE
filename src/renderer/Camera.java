package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import renderer.PixelManager;

import static primitives.Util.*;

/**
 * Camera class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class Camera {
    //-----------------------------------------------------------
    //FIELDS
    /*
     * The location of the camera - point of view of the camera.
     */
    private Point p0;
    /**
     * the vector that points onwards relative to the camera
     */
    private Vector v_to;
    /**
     * the vector that points upwards relative to the camera
     */
    private Vector v_up;
    /**
     * the vector that points to the right side relative to the camera
     */
    private Vector v_right;

    /**
     * distance between the camera and the view plane
     */
    private double distance;

    /**
     * The width of the view plane.
     */
    private double width;
    /**
     * The height of the view plane.
     */
    private double height;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    /**
     * number of rays in a beam
     */
    private int numberOfBeam = 90;
    /**
     * level for adaptive super sampling (AA)
     */
    private int maxLevelAdaptiveSuperSampling = 3;
    /**
     * we want enough threads but not too much
     */
    private int threadCount = 3;

    //-----------------------------------------------------------
    //CONSTRUCTOR

    /**
     * constructor to initialize camera
     *
     * @param p0   - camera's location
     * @param v_to - camera's towards direction
     * @param v_up - camera's up direction
     */
    public Camera(Point p0, Vector v_to, Vector v_up) throws IllegalArgumentException {

        //The vectors vTo and vUp must be orthogonal
        if (!isZero(v_to.dotProduct(v_up)))
            throw new IllegalArgumentException("The vectors vTo and vUp are not orthogonal");

        this.p0 = p0;

        //Normalize the vectors
        this.v_to = v_to.normalize();
        this.v_up = v_up.normalize();

        //create v_right
        v_right = v_to.crossProduct(v_up).normalize();
    }

    //-----------------------------------------------------------
    //GETTERS

    /**
     * Get p0
     *
     * @return point of the camera position
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get v_to
     *
     * @return camera's towards direction
     */
    public Vector getV_to() {
        return v_to;
    }

    /**
     * Get v_up
     *
     * @return camera's up direction
     */
    public Vector getV_up() {
        return v_up;
    }

    /**
     * Get v_right
     *
     * @return camera's right direction
     */
    public Vector getV_right() {
        return v_right;
    }

    /**
     * Get distance
     *
     * @return distance of the view plane from the camera
     */
    public double get_distance() {
        return distance;
    }

    /**
     * Get width
     *
     * @return width of the view plane
     */
    public double get_width() {
        return width;
    }

    /**
     * Get height of the view plane
     *
     * @return height of the view plane
     */
    public double get_height() {
        return height;
    }

    //-----------------------------------------------------------
    //SETTERS

    /**
     * Set view plane size
     *
     * @param width  The width of the view plane
     * @param height The height of the view plane
     * @return camera instance
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;

        return this;
    }

    /**
     * Set view plane distance
     *
     * @param distance distance between camara and view plane
     * @return camera instance
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Set camera's image writer
     *
     * @param imageWriter image writer
     * @return camera instance
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Set camera's ray tracer base
     *
     * @param rayTracer ray tracer base
     * @return camera instance
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * set maximum level to sample
     *
     * @param maxLevelAdaptiveSuperSampling max level
     * @return camera instance
     */
    public Camera setMaxLevelAdaptiveSuperSampling(int maxLevelAdaptiveSuperSampling) {
        this.maxLevelAdaptiveSuperSampling = maxLevelAdaptiveSuperSampling;
        return this;
    }

    /**
     * turn on anti aliasing improvement
     *
     * @param level level of rays constructing for anti aliasing
     * @return camera instance
     */
    public Camera antiAliasingOn(int level) {
        if (level < 0) throw new IllegalArgumentException("level can not be negative");
        this.maxLevelAdaptiveSuperSampling = level;
        return this;
    }

    /**
     * turn off anti aliasing improvement
     *
     * @return camera instance
     */
    public Camera antiAliasingOff() {
        this.maxLevelAdaptiveSuperSampling = 0;
        return this;
    }

    /**
     * turn on multi threading improvement
     *
     * @param count new thread count
     * @return camera instance
     */
    public Camera multiThreadingOn(int count) {
        if (count < 1) throw new IllegalArgumentException("thread count can must be positive");
        this.threadCount = count;
        return this;
    }

    /**
     * torn off multi threading improvement
     *
     * @return camera instance
     */
    public Camera multiThreadingOff() {
        this.threadCount = 1;
        return this;
    }

    /**
     * construct Ray - create ray in the given resolution
     *
     * @param Nx number of columns
     * @param Ny number of rows
     * @param j  index of pixel (column)
     * @param i  index of pixel (rows)
     * @return null
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        double Ry = (double) height / Ny;
        double Rx = (double) width / Nx;
        Point center_p = p0.add(v_to.scale(distance)); // center point
        double rightScale = alignZero((j - (Nx / 2d)) * Rx + Rx / 2d);
        double upScale = -1 * alignZero((i - (Ny / 2d)) * Ry + Ry / 2d);
        if (!isZero(rightScale)) {
            center_p = center_p.add(v_right.scale(rightScale));
        }
        if (!isZero(upScale)) {
            center_p = center_p.add(v_up.scale(upScale));
        }
        return new Ray(p0, center_p.subtract(p0));
    }

    /**
     * for super sampling - with random method
     * construct beam of ray throw random point in every pixel
     *
     * @param nX number of rows
     * @param nY number of columns
     * @param j  index of pixel (column)
     * @param i  index of pixel (rows)
     * @return beam with (number of beam) rays
     */
    public List<Ray> constructBeam(int j, int i, int nX, int nY) {
        List<Ray> beam = new LinkedList<>();
        beam.add(constructRay(nX, nY, j, i));
        double rY = (double) height / nY;
        double rX = (double) width / nX;
        Point pixelCenter = p0.add(v_to.scale(distance)); // center point
        double rightScale = alignZero((j - (nX / 2d)) * rX + rX / 2d);
        double upScale = alignZero((i - (nY / 2d)) * rY + rY / 2d);
        Random rand = new Random();
        if (!isZero(rightScale)) {
            pixelCenter = pixelCenter.add(v_right.scale(rightScale));
        }
        if (!isZero(upScale)) {
            pixelCenter = pixelCenter.add(v_up.scale(-1 * upScale));
        }
        for (int k = 0; k < numberOfBeam; k++) {
            double lX = rX * (rand.nextBoolean() ? rand.nextDouble() : -1 * rand.nextDouble());
            double lY = rY * (rand.nextBoolean() ? rand.nextDouble() : -1 * rand.nextDouble());
            Point randPoint = pixelCenter;
            if (!isZero(lX)) randPoint = randPoint.add(v_right.scale(lX));
            if (!isZero(lY)) randPoint = randPoint.add(v_up.scale(-1 * lY));
            beam.add(new Ray(p0, randPoint.subtract(p0)));
        }
        return beam;
    }

    /**
     * render image
     *
     * @return camera after produce image
     */
    public Camera renderImage() {
        if (v_up == null || v_to == null || v_right == null || distance == 0 || p0 == null || width == 0 || height == 0
                || rayTracer == null || imageWriter == null) {
            throw new MissingResourceException("can not render image when one of camera's field is null", "", "");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; ++i) {
            for (int j = 0; j < nY; ++j) {
                Color color = castRay(j, i, nX, nY);
                imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * produce image with super sampling using random method
     *
     * @return camera
     */
    public Camera renderImageSuperSampling() {
        // check exception
        if (v_up == null || v_to == null || v_right == null || distance == 0 || p0 == null || width == 0 || height == 0
                || rayTracer == null || imageWriter == null) {
            throw new MissingResourceException("can not render image when one of camera's field is null", "", "");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        // construct beam of rays through every pixel
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Color color = castBeam(j, i, nX, nY);
                imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * render image with adaptive super sampling method - calls castBeamASS for each pixel
     *
     * @return camera instance
     */
    public Camera renderImageAdaptiveSuperSampling() {
        // check exception
        if (v_up == null || v_to == null || v_right == null || distance == 0 || p0 == null || width == 0 || height == 0
                || rayTracer == null || imageWriter == null) {
            throw new MissingResourceException("can not render image when one of camera's field is null", "", "");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Color color = castBeamASS(j, i, nX, nY);
                imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * render new image with multi threading improvement
     *
     * @return camera instance
     */
    public Camera renderImageMultiThreading() {
        // check exception
        if (v_up == null || v_to == null || v_right == null || distance == 0 || p0 == null || width == 0 || height == 0
                || rayTracer == null || imageWriter == null) {
            throw new MissingResourceException("can not render image when one of camera's field is null", "", "");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        new PixelManager(nX, nY, 60);
        while (threadCount-- > 0) {
            new Thread(() -> {
                for (PixelManager pixel = new PixelManager(nX, nY, 60); pixel.nextPixel() != null; pixel.pixelDone())
                    imageWriter.writePixel(pixel.getcCol(), pixel.getcRow(), castRay(pixel.getcCol(), pixel.getcRow(), nX, nY));
            }).start();
        }
        // pixel.wait to finish
        return this;
    }

    /**
     * render new image with multi threading and adaptive super sampling improvements
     *
     * @return camera instance
     */
    public Camera renderImageMultiThreadingASS() {
        // check exception
        if (v_up == null || v_to == null || v_right == null || distance == 0 || p0 == null || width == 0 || height == 0
                || rayTracer == null || imageWriter == null) {
            throw new MissingResourceException("can not render image when one of camera's field is null", "", "");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        new PixelManager(nX, nY, 60);
        while (threadCount-- > 0) {
            new Thread(() -> {
                for (PixelManager pixel = new PixelManager(nX, nY, 60); pixel.nextPixel() != null; pixel.pixelDone()) {
                    Color color = castBeamASS(pixel.getcCol(), pixel.getcRow(), nX, nY);
                    imageWriter.writePixel(pixel.getcCol(), pixel.getcRow(), color);
                }
            }).start();
        }
        // pixel.wait to finish
        return this;
    }

    /**
     * if there is an image writer - create grid according to interval
     *
     * @param interval size for grid
     * @param color    color of grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("can not create a grid while image writer is null", "image writer", "");
        }
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
    }

    /**
     * calls imageWriter write to image
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("an not write to image while image writer is null", "imageWriter", "");
        }
        imageWriter.writeToImage();
    }

    /**
     * gives ray's color
     *
     * @param j  pixel column
     * @param i  pixel row
     * @param nX number of rows
     * @param nY number of columns
     * @return color of ray throw pixel
     */
    private Color castRay(int j, int i, int nX, int nY) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        return color;
    }

    /**
     * calculate average color of all rays in beam (rays intersect with one pixel)
     *
     * @param j  pixel column
     * @param i  pixel row
     * @param nX number of rows
     * @param nY number of columns
     * @return pixel's color
     */
    private Color castBeam(int j, int i, int nX, int nY) {
        List<Ray> beam = constructBeam(j, i, nX, nY);
        Color color = Color.BLACK;
        for (Ray ray : beam) // calculate average color
        {
            color = color.add(rayTracer.traceRay(ray));
        }
        return color.reduce(numberOfBeam);
    }

    /**
     * cast beam with adaptive super sampling method - calls calcColorASS
     *
     * @param j  pixel index (column)
     * @param i  pixel index (rows)
     * @param nX number of roes
     * @param nY number of columns
     * @return pixel color
     */
    private Color castBeamASS(int j, int i, int nX, int nY) {
        Ray center = constructRay(nX, nY, j, i); // ray throw pixel center
        Color centerColor = rayTracer.traceRay(center); // color of pixel center
        return calcColorASS(j, i, nX, nY, maxLevelAdaptiveSuperSampling, centerColor);
    }

    /**
     * calculate recursively pixel color - divide to 4 mini pixels, calculate colors, divide..
     *
     * @param j           pixel index (column)
     * @param i           pixel index (rows)
     * @param nX          number of rows
     * @param nY          number of columns
     * @param level       of recursive
     * @param centerColor center pixel color
     * @return average of points from pixel
     */
    private Color calcColorASS(int j, int i, int nX, int nY, int level, Color centerColor) {
        if (level == 0) return centerColor;
        Color color = centerColor;
        // divide pixel to 4
        Ray[] beam = new Ray[]{constructRay(nX * 2, nY * 2, j * 2, i * 2),
                constructRay(nX * 2, nY * 2, j * 2, i * 2 + 1),
                constructRay(nX * 2, nY * 2, j * 2 + 1, i * 2),
                constructRay(nX * 2, nY * 2, j * 2 + 1, i * 2 + 1)};
        for (int ray = 0; ray < 4; ray++) {
            Color miniPixelColor = rayTracer.traceRay(beam[ray]);
            if (!miniPixelColor.equals(centerColor)) {
                miniPixelColor = calcColorASS(2 * j + ray / 2, 2 * i + ray % 2, 2 * nX, 2 * nY,
                        level - 1, miniPixelColor);
            }
            color = color.add(miniPixelColor);
        }
        return color.reduce(5); // average of each quarter and center point colors
    }
}
