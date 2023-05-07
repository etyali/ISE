package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry extends Intersectable{
    /**
     * to do in implements classes
     */
    Vector getNormal(Point p) throws Exception;
}
