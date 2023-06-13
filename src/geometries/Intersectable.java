package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
    //public abstract List<Point> findIntersections(Ray ray);
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     *
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
