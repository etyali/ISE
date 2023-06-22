package unittest.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;
import primitives.Color;

/**
 * tests for improvement super sampling
 * author Etya Lichtman and Orly Salem
 */
public class SuperSamplingTests {
    /**
     * test for module basicRenderMultiColorTest with super sampling improvement
     *
     * @throws Exception if something went wrong
     */
    @Test
    public void basicRenderMultiColorTestSuperSampling() throws Exception {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //

        scene.geometries.add( // center
                new Sphere(50, new Point(0, 0, -100)),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("color render test Sus", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImageSuperSampling();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();
    }
}
