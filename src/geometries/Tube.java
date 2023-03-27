package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    public Ray axisRay;

    /**
     * Tube constructor
     *
     * @param radius  will be the RadialGeometry's radius
     * @param axisRay will be the ray
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
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
