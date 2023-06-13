package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.*;

public class Ray {
    public Point p0;
    public Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
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
     * @return The direction's vector
     */
    public Vector getDir() {
        return dir;
    }

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
     * @param pointList list of points
     * @return The closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
    /*public Point findClosestPoint(List<Point> pointList) {
        if (pointList == null || pointList.isEmpty()) {
            return null;
        }
        Point cPoint = pointList.get(0);
        double minDS = p0.distanceSquared(cPoint);
        for (Point p : pointList) {
            double dis = p0.distanceSquared(p);
            if (dis < minDS) {
                minDS = dis;
                cPoint = p;
            }
        }
        return cPoint;
    }*/

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
