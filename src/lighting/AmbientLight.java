package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    Color intensity;
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructor to initialize Ambient Light
     * @param Ia basic light intensity
     * @param Ka factor of intensity
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        intensity = Ia.scale(Ka);
    }

    public AmbientLight(Color Ia, double Ka) {
    }

    public Color getIntensity()
    {
        return intensity;
    }
}
