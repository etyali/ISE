package geometries;

import primitives.*;

/**
 * cylinder class
 *
 * @author Etya Lichtman and Orly Salem
 */

public class Cylinder extends Tube {
    /**
     * cylinder height
     */
    public double height;

    /**
     * Cylinder constructor
     *
     * @param radius  will be the RadialGeometry's radius
     * @param axisRay will be the ray
     * @param height  will be the height
     */
    public Cylinder(Ray axisRay, double height, double radius) {
        super(radius, axisRay);
        this.height = height;
    }


    /**
     * get Normal - construct normal to cylinder
     *
     * @param p- point to compute normal to
     * @return normal to point on cylinder
     */
    public Vector getNormal(Point p) throws IllegalArgumentException {

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        double radius = this.radius;
        // point on base
        if (p.distance(p0) <= radius) {
            return v;
        }

        Vector vOfAnotherBase = axisRay.getDir().scale(height);
        Point p1 = p0.add(vOfAnotherBase);
        // point on another base
        if (p.distance(p1) <= radius) {
            return v;
        }
        // tube's get normal
        return super.getNormal(p);
    }
}
