package lighting;

import primitives.Color;

/**
 *
 */
abstract class Light {
    /**
     * intensity of light
     */
    final private Color intensity;

    /**
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
