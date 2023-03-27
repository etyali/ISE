package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    public double height;

    /**
     * Cylinder constructor
     *
     * @param radius  will be the RadialGeometry's radius
     * @param axisRay will be the ray
     * @param height  will be the height
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
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
