package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry {
    public Point q0;
    public Vector normal;

    /**
     * Plane constructor
     *
     * @param q0     will be the base point
     * @param normal will be the normal to plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Plane constructor, for now normal = null
     *
     * @param v1 will be the d1 of base point
     * @param v2 will be the d2 of base point
     * @param v3 will be the d3 of base point
     */
    public Plane(Point v1, Point v2, Point v3) {
        this.normal = null;
        this.q0 = v1;
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * getNormal
     *
     * @return normal
     */
    public Vector getNormal() {
        return normal;
    }
    public List<Point> findIntsersections(Ray ray){return null;}
}
