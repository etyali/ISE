package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.*;

/**
 * Ray class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class Ray {
    /**
     * ray's point
     */
    public Point p0;
    /**
     * ray's direction vector
     */
    public Vector dir;

    private static final double DELTA = 0.1;

    /**
     * Ray constructor - initialize point p0 and vector direction dir
     *
     * @param p0  ray point
     * @param dir direction vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * ray constructor
     *
     * @param p0     new ray point that closer to reflection surface
     * @param dir    ray direction vector
     * @param normal to geometry
     */
    public Ray(Point p0, Vector dir, Vector normal) {
        Vector delta = normal.scale(Util.alignZero(normal.dotProduct(dir.normalize())) > 0 ? DELTA : -DELTA);
        Point point = p0.add(delta.normalize());
        this.p0 = point;
        this.dir = dir.normalize();
    }

    /**
     * Get the start point of the ray
     *
     * @return P0 - the start point of ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get the direction of the ray
     *
     * @return direction's vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Get the point of the ray
     *
     * @return ray's point
     */
    public Point getPoint(double t) throws IllegalArgumentException {
        return p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * return the closest point to the ray's point (p0) from list of points
     *
     * @param points list of points
     * @return The closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * return the closest point to the ray's point (p0) and the geometry the point is on from list of GeoPoints
     *
     * @param geoPointList list of points
     * @return The closest point and the geometry it's on
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList == null || geoPointList.isEmpty()) {
            return null;
        }
        GeoPoint cGPoint = geoPointList.get(0);
        double minDS = p0.distanceSquared(cGPoint.point);
        for (GeoPoint p : geoPointList) {
            double dis = p0.distanceSquared(p.point);
            if (dis < minDS) {
                minDS = dis;
                cGPoint = p;
            }
        }
        return cGPoint;
    }
}
