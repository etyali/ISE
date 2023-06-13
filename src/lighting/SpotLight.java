package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {
    private Vector direction;

    int narrowBeam = 1;

    /**
     * constructor - initialize Light intensity, Point Light position and direction
     *
     * @param intensity Light intensity
     * @param position  Point light position
     * @param dir       Spotlight direction
     */
    public SpotLight(Color intensity, Point position, Vector dir) {
        super(intensity, position);
        direction = dir.normalize();
    }

    /**
     * @param narrowBeam
     * @return
     */
    public SpotLight setNarrowBeam(int narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    /**
     * calculate light intensity
     *
     * @param p point on geometry
     * @return
     */
    public Color getIntensity(Point p) {
        //if (direction == null) return Color.BLACK;
        return super.getIntensity(p).scale(Math.max(0d, direction.dotProduct(super.getL(p))));
    }

}
