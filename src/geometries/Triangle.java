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
        if (vertices.length != 3) {
            throw new IllegalArgumentException("A Triangle can't have other than 3 vertices");
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> l = plane.findIntersections(ray);
        if (l == null) return null;
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);
        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);
        Double dp1 = Util.alignZero(v.dotProduct(n1));
        Double dp2 = Util.alignZero(v.dotProduct(n2));
        Double dp3 = Util.alignZero(v.dotProduct(n3));
        if ((dp1 > 0 && dp2 > 0 && dp3 > 0) || (dp1 < 0 && dp2 < 0 && dp3 < 0))
            return l;
        else return null;

    }
}
