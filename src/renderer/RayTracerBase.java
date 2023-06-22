package renderer;

import primitives.*;
import geometries.*;
import scene.Scene;

/**
 * Ray Tracer Base abstract class
 *
 * @author Etya Lichtman and Orly Salem
 */
public abstract class RayTracerBase {
    /**
     * scene object
     */
    protected Scene scene;

    /**
     * Constructor to initialize scene of ray tracer base
     *
     * @param scene initial value for scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * tracer ray
     *
     * @param ray parameter for function
     */
    public abstract Color traceRay(Ray ray);
}
