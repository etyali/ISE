package lighting;

import primitives.*;

public class AmbientLight extends Light {
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Constructor to initialize Ambient Light - calls Light constructor
     *
     * @param Ia basic light intensity
     * @param Ka factor of intensity
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }

    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(new Double3(Ka)));
    }


}
