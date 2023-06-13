package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {

    public String name; //Scene's name
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;

    public Geometries geometries = new Geometries(); //3D model

    public List<LightSource> lights = new LinkedList<>();

    /**
     * @param name
     */
    public Scene(String name) {
        this.name = name;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * @param ambientLight
     * @return
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * @param geometries
     * @return
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * @param lights
     * @return
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
