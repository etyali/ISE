package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry {
    public Point p0;
    public Vector normal;

    /**
     * Plane constructor
     *
     * @param p0     will be the base point
     * @param normal will be the normal to plane
     */
    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        this.normal = normal.normalize();
    }

    /**
     * Plane constructor, for now normal = null
     *
     * @param p1 will be the d1 of base point
     * @param p2 will be the d2 of base point
     * @param p3 will be the d3 of base point
     */
    public Plane(Point p1, Point p2, Point p3) throws Exception {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        normal = (v1.crossProduct(v2)).normalize();
        p0 = p1;
    }

    /**
     * getNormal
     *
     * @return normal
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
