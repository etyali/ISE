package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 */
public interface LightSource {
    /**
     * To implement in inheritance classes
     *
     * @param p point
     * @return intensity
     */
    public Color getIntensity(Point p);

    /**
     * To implement in inheritance classes
     *
     * @param p point
     * @return light
     */
    public Vector getL(Point p);

    /**
     * @param point point on geometry
     * @return distance between light source to point
     */
    double getDistance(Point point);


}
