package unittest.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for shadow, reflection and transparency functionality - final targil 7
 *
 * @author etya and orly
 */
public class ourReflectionRefractionTest {
    private Scene scene = new Scene("Our reflection and refraction scene");

    /**
     * Produce a picture of four spheres and a triangle lighted by a spotlight
     */
    @Test
    public void triangleInSpheres() throws Exception {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(25d, new Point(-25, 0, 0)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.4).setKs(0.3).setnShininess(100).setkT(0.3)),
                new Sphere(14.5, new Point(-25, 0, 0)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.5).setKs(0.5).setnShininess(100)),
                new Triangle(new Point(-25, 0, -50), new Point(0, 50, -50), new Point(25, 0, -50))
                        .setEmission(new Color(GREEN)).setMaterial(new Material().setkD(0.3).setKs(0.3).setnShininess(90)),
                new Sphere(25d, new Point(25, 0, -100)).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setkD(0.4).setKs(0.3).setnShininess(100).setkT(0.3)),
                new Sphere(14.5, new Point(25, 0, -100)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setKs(0.5).setnShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));

        camera.setImageWriter(new ImageWriter("TriangleInSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
