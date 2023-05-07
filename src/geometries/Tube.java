package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
     * @param p- to compute normal
     * @return null for now
     */
    public Vector getNormal(Point p) throws Exception {

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        Point orToP; //orthogonal to P
        double t = v.dotProduct(p.subtract(p0));

        if (t == 0)
        {
            orToP = p0;
        }
        else
        {
            orToP = p0.add(v.scale(t));
        }

        return p.subtract(orToP).normalize();
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
