package lighting;

import primitives.Color;

/**
 * Light class (abstract)
 *
 * @author Etya Lichtman and Orly Salem
 */
abstract class Light {
    /**
     * intensity of light
     */
    final private Color intensity;

    /**
     * light constructor - initialize intensity
     *
     * @param intens new Light intensity
     */
    protected Light(Color intens) {
        intensity = intens;
    }

    /**
     * @return light intensity
     */
    public Color getIntensity() {
        return intensity;
    }


}
