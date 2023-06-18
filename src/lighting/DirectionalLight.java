package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {
    /**
     * directional light direction vector
     */
    final private Vector direction;

    /**
     * direction light constructor - initialize Light intensity and direction
     *
     * @param intensity Light intensity
     * @param dir       light direction
     */
    public DirectionalLight(Color intensity, Vector dir) {
        super(intensity);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;//.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

}
