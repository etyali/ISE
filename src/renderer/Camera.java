package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

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
     * @param p0  - camera's location
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
        v_right = v_to.crossProduct(v_up);
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
     * @param width The width of the view plane
     * @param height The height of the view plane
     * @return camera instance
     */
    public Camera setVPSize(double width, double height)
    {
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
    public Camera setVPDistance(double distance)
    {
        this.distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i)
    {
        return null;
    }

}
