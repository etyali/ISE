package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Point Light class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class PointLight extends Light implements LightSource {
    /**
     * point light position
     */
    final private Point position;
    /**
     * attenuation factors
     */
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * constructor - initialize Light intensity and point light position
     *
     * @param intensity Light intensity
     * @param pos       point light position
     */
    public PointLight(Color intensity, Point pos) {
        super(intensity);
        position = pos;
    }

    //----------------------------SETTERS-------------------------------

    public PointLight setkC(double Kc) {
        kC = Kc;
        return this;
    }

    public PointLight setkL(double Kl) {
        kL = Kl;
        return this;
    }

    public PointLight setkQ(double Kq) {
        kQ = Kq;
        return this;
    }
    //----------------------------GETTERS-------------------------------

    /**
     * calculate intensity for point light (e.g bulb)
     *
     * @param p point on geometry
     * @return intensity
     */
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double ds = p.distanceSquared(position);
        //return super.getIntensity().scale(kC + kL * d + kQ * ds);
        return super.getIntensity().reduce(kC + kL * d + kQ * ds);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

}
