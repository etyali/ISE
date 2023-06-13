package renderer;

import primitives.*;
import geometries.*;
import scene.Scene;


public abstract class RayTracerBase {
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
