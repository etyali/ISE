package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.*;

public class Triangle extends Polygon {
    /**
     * Triangle constructor
     *
     * @param vertices will be the list of 3 vertices
     */
    public Triangle(Point... vertices) throws Exception {
        super(vertices);
    }

    /**
     * find all intersections between triangle and given ray
     *
     * @param ray         intersection ray
     * @param maxDistance maximum distance to compute
     * @return list of geo point (triangle, intersection)
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> l = plane.findGeoIntersectionsHelper(ray, maxDistance);
        if (l == null) return null;
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector v1 = vertices.get(0).subtract(p0).normalize();
        Vector v2 = vertices.get(1).subtract(p0).normalize();
        Vector v3 = vertices.get(2).subtract(p0).normalize();
        Vector n1 = v1.crossProduct(v2).normalize().normalize();
        Vector n2 = v2.crossProduct(v3).normalize().normalize();
        Vector n3 = v3.crossProduct(v1).normalize().normalize();
        Double dp1 = Util.alignZero(v.dotProduct(n1));
        Double dp2 = Util.alignZero(v.dotProduct(n2));
        Double dp3 = Util.alignZero(v.dotProduct(n3));
        if ((dp1 > 0 && dp2 > 0 && dp3 > 0) || (dp1 < 0 && dp2 < 0 && dp3 < 0))
            return List.of(new GeoPoint(this, l.get(0).point));
        else return null;
    }
}
