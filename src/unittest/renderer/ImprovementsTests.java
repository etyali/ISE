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
 * test of improvements - anti aliasing, adaptive super sampling, multi threading
 *
 * @author Etya Lichtman and Orly Salem
 */
public class ImprovementsTests {
    @Test
    public void antiAliasingASSTest() throws Exception {
        /**
         * test for module basicRenderMultiColorTest with anti aliasing - adaptive super sampling improvement
         *
         * @throws Exception if something went wrong
         */
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
                .setImageWriter(new ImageWriter("color render test AAASS on", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        // with anti aliasing and adaptive super sampling
        camera.renderImageAdaptiveSuperSampling();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();
        // without anti aliasing
        camera.antiAliasingOff();
        camera.setImageWriter(new ImageWriter("color render test AAASS off", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImageAdaptiveSuperSampling();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();
        //turn AA on
        camera.antiAliasingOn(3);
    }

    @Test
    public void multiThreadingOnTest() throws Exception {
        /**
         * test for module basicRenderMultiColorTest with multi threading
         *
         * @throws Exception if something went wrong
         */

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
                .setImageWriter(new ImageWriter("color render test multi threading on", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImageMultiThreading();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();

    }

    @Test
    public void multiThreadingOffTest() throws Exception {
        /**
         * test for module basicRenderMultiColorTest with multi threading off
         *
         * @throws Exception if something went wrong
         */

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
                .multiThreadingOff()
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("color render test multi threading off", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImageMultiThreading();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();

        camera.multiThreadingOn(3);

    }
}
