package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
    public Vector getNormal(Point p) throws Exception {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
