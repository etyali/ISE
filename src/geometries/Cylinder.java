package geometries;

import primitives.*;

public class Cylinder extends Tube {
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
     * getNormal
     *
     * @param p- to compute normal
     * @return null for now
     */
    public Vector getNormal(Point p) throws Exception {

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        double radius = this.radius;

        if (p.distance(p0) <= radius) {
            return v;
        }

        Vector vOfAnotherBase = axisRay.getDir().scale(height);
        Point p1 = p0.add(vOfAnotherBase);

        if (p.distance(p1) <= radius) {
            return v;
        }


        return super.getNormal(p);
    }
}
