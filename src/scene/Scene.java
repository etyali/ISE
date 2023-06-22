package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class Scene {

    public String name; //Scene's name
    public Color background = Color.BLACK; // scene background - initialize to black
    public AmbientLight ambientLight = AmbientLight.NONE; // scene ambient light - initialize to none

    public Geometries geometries = new Geometries(); //3D model

    public List<LightSource> lights = new LinkedList<>(); // lights in scene

    /**
     * scene constructor
     *
     * @param name scene name
     */
    public Scene(String name) {
        this.name = name;
    }

    //----------------------------SETTERS-------------------------------
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
    
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
