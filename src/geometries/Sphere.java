package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere extends RadialGeometry {

    public Point center;

    /**
     * Sphere constructor
     *
     * @param radius will be the RadialGeometry's radius
     * @param center will be the center of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * getNormal
     *
     * @param p- to compute normal
     * @return null for now
     */
    public Vector getNormal(Point p) throws IllegalArgumentException {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        //Ray starts at the center of sphere
        if (center.equals(p0)) {
            Point p1 = ray.getPoint(radius);
            List<Point> intersectRaySphere = List.of(p1);

            return intersectRaySphere;
        }

        Vector u = center.subtract(p0);

        double t_m = dir.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - (t_m * t_m));
        //ray does not intersect the sphere
        if (d >= radius) {
            return null;
        }
        double t_h = Math.sqrt(radius * radius - d * d);
        double t1 = t_m - t_h;
        double t2 = t_m + t_h;

        List<Point> intersectRaySphere;

        if(t1 > 0 && t2 > 0)
        {
            intersectRaySphere  = List.of(ray.getPoint(t1), ray.getPoint(t2));
            return intersectRaySphere;
        }

        if (t1 > 0) {
            return List.of(ray.getPoint(t1));
        }

        if (t2 > 0) {
            return List.of(ray.getPoint(t2));
        }

        return null;
    }
}
