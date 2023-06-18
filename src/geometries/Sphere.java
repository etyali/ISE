package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere extends RadialGeometry {
    /**
     * sphere's center point
     */
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
        Vector dir = ray.getDir().normalize();
        //Ray starts at the center of sphere
        if (center.equals(p0)) {
            Point p1 = ray.getPoint(radius);
            List<Point> intersectRaySphere = List.of(p1);
            return intersectRaySphere;
        }

        Vector u = center.subtract(p0);

        double t_m = u.dotProduct(dir);
        double d = Math.sqrt(u.lengthSquared() - (t_m * t_m));
        //ray does not intersect the sphere
        if (d >= radius) {
            return null;
        }
        double t_h = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(t_m - t_h);
        double t2 = alignZero(t_m + t_h);

        List<Point> intersectRaySphere;
        if (t1 > 0 && t2 > 0) {
            intersectRaySphere = List.of(ray.getPoint(t1), ray.getPoint(t2));
            return intersectRaySphere;
        }

        if (t1 <= 0) {
            if (t2 <= 0) {
                return null;
            }
            return List.of(ray.getPoint(t2));
        }
        if (t2 <= 0) {
            return List.of(ray.getPoint(t1));
        }
        return null;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir().normalize();
        //Ray starts at the center of sphere
        if (center.equals(p0)) {
            Point p1 = ray.getPoint(radius);
            List<GeoPoint> intersectRaySphere = List.of(new GeoPoint(this, p1));
            return intersectRaySphere;
        }

        Vector u = center.subtract(p0);

        double t_m = u.dotProduct(dir);
        double d = Math.sqrt(u.lengthSquared() - (t_m * t_m));
        //ray does not intersect the sphere
        if (d >= radius) {
            return null;
        }
        double t_h = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(t_m - t_h);
        double t2 = alignZero(t_m + t_h);

        List<GeoPoint> intersectRaySphere;
        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            intersectRaySphere = List.of(new GeoPoint(this, ray.getPoint(t1)),
                    new GeoPoint(this, ray.getPoint(t2)));
            return intersectRaySphere;
        }

        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            if (t2 > 0 && alignZero(t2 - maxDistance) <= 0)
                return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t1))),
                        new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t2))));
            return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t1))));

        } else if (t2 > 0 && alignZero(t2 - maxDistance) <= 0)
            return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t2))));
        return null;
    }
}
