package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

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
     * construct Ray - creat ray in the given resolution
     *
     * @param Nx number of columns
     * @param Ny number of rows
     * @param j  index of pixel (column)
     * @param i  index of pixel (rows)
     * @return null
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        //View Plane 3x3 (WxH 6x6) - Central, Corner, Side pixels
        //View Plane 4x4 (WxH 8x8) - Inside, Corner, Side pixels
        double Rx = (double)width / Nx;
        double Ry = (double)height / Ny;
        Point center_p = p0.add(v_to.scale(distance)); // center point
        double rightScale = alignZero(j - (Nx / 2)) * Rx + Rx / 2;
        double upScale = alignZero(i - (Ny / 2)) * Ry + Ry / 2;
        if (!isZero(rightScale)) {
            center_p.add(v_right.scale(rightScale));
        }
        if (!isZero(upScale)) {
            center_p.add(v_up.scale(-1 * upScale));
        }
        return new Ray(p0, center_p.subtract(p0));
    }

}
