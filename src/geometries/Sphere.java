package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
     * @param point- to compute normal
     * @return null for now
     */
    public Vector getNormal(Point point) {
        return null;
    }

}
