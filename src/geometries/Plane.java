package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

/**
 * Plane class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class Plane extends Geometry {
    /**
     * point on plane
     */
    public Point p0;
    /**
     * normal to plane
     */
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
     * Plane constructor, normal is v1 cross product v2
     *
     * @param p1 will be the d1 of base point
     * @param p2 will be the d2 of base point
     * @param p3 will be the d3 of base point
     */
    public Plane(Point p1, Point p2, Point p3) throws IllegalArgumentException {
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

    /**
     * getNormal
     *
     * @return normal
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * geo intersection between plane and given ray
     *
     * @param ray         intersection ray
     * @param maxDistance maximum distance to compute
     * @return list of one geo point - (plane, intersection)
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point ray_point = ray.getP0();
        Vector ray_vector = ray.getDir();
        double t;
        double nv = normal.dotProduct(ray_vector);  // n * v
        // ray starts in plane's point
        if (ray_point.equals(p0)) {
            return null;
        }
        if (isZero(nv))  // ray orthogonal to plane
        {
            t = alignZero(normal.dotProduct(p0.subtract(ray_point)));
        } else {
            t = alignZero((normal.dotProduct(p0.subtract(ray_point))) / nv);
        }
        if (t <= 0 || isZero(t) || alignZero(t - maxDistance) > 0)  // ray start in/after plane
        {
            return null;
        }
        List<GeoPoint> intersections = List.of(new GeoPoint(this, ray.getPoint(t)));
        return intersections;
    }
}
