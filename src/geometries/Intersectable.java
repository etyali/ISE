package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
    //public abstract List<Point> findIntersections(Ray ray);

    /**
     * return all intersections between ray and geometries list
     *
     * @param ray ray for intersections
     * @return null if there is no intersection, list of intersections point else
     */
    /*public List<Point> findIntersections(Ray ray, double maxDistance) {
        var geoList = findGeoIntersections(ray, maxDistance);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }*/

    public abstract List<Point> findIntersections(Ray ray);

    /**
     * to implement in all find geo intersections classes
     *
     * @param ray ray for intersections
     * @return list of intersections
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * calls findGeoIntersectionsHelper
     *
     * @param ray ray for intersections checking
     * @return GeoPoint intersections list
     */
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * class - GeoPoint
     * contain geometry, point on geometry
     */
    public static class GeoPoint {
        /**
         * geometry of which the point is
         */
        public Geometry geometry;

        /**
         * coordinates of point
         */
        public Point point;

        /**
         * constructor - set new GeoPoint
         *
         * @param geometry new geometry
         * @param point    new point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }


}
