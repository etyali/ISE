package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    public Point q0;
    public Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point v1, Point v2, Point v3) {
        this.normal = null;
        this.q0 = v1;
    }

    @Override ////////////////////
    public Vector getNormal(Point p) {
        return null;
    }
    public Vector getNormal() {
        return null;
    }
}
