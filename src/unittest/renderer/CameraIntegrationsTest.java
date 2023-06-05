package unittest.renderer;

import geometries.Intersectable;
import geometries.Sphere;
import geometries.Plane;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CameraIntegrationsTest {

    static final Point ZERO_POINT = new Point(0, 0, 0);
    Camera camera = new Camera(new Point(0, 0, 0),
            new Vector(0, 1, 0),
            new Vector(0, 0, 1))
            .setVPSize(3, 3)
            .setVPDistance(1);
    Camera camera2 = new Camera(new Point(0, 0, 0.5),
            new Vector(0, -1, 0),
            new Vector(0, 0, -1))
            .setVPSize(3, 3)
            .setVPDistance(1);
    /**
     * camera for plane intersection
     */
    Camera camera3 = new Camera(new Point(0, 0, 0),
            new Vector(0, 0, 1),
            new Vector(0, 1, 0))
            .setVPSize(3, 3)
            .setVPDistance(1);

    /**
     * test number of intersections between rays from the view plane and a sphere
     */
    @Test
    void testIntersectionsRaySphere() {

        // TC01: sphere behind the view plane - no intersections points
        assertEquals(0, countIntersectionsCameraGeometry(camera2, 3, 3,
                        new Sphere(0.5, new Point(0, 0, 1))),
                "wrong number of intersections points - sphere behind view plane should be 0");

        //TC02: sphere in front of view plane, radius 1 - 2 intersections points
        assertEquals(2, countIntersectionsCameraGeometry(camera, 3, 3,
                        new Sphere(1, new Point(0, 0, -3))),
                "wrong number of intersections points - sphere in front of view plane (radius 1) should be 2");

        // TC03: view plane inside sphere - 9 intersections points
        assertEquals(9, countIntersectionsCameraGeometry(camera2, 3, 3,
                        new Sphere(4, new Point(0, 0, 0))),
                "wrong number of intersections points - view plane inside sphere should be 9");

        // TC04: sphere bigger than view plane - 18 intersections points
        assertEquals(18, countIntersectionsCameraGeometry(camera2, 3, 3,
                        new Sphere(2.5, new Point(0, 0, -2.5))),
                "wrong number of intersections points - sphere bigger than view plane should be 18");

        // TC05: view plane edges doesn't intersect with the sphere, radius 2- 10 intersections
        assertEquals(10, countIntersectionsCameraGeometry(camera2, 3, 3,
                        new Sphere(2, new Point(0, 0, -2))),
                "wrong number of intersections points - radius 2 should be 10");

    }

    /**
     * test number of intersections between rays from the view plane and a plane
     */
    @Test
    void testIntersectionsRayPlane() {
        Plane plane = new Plane(new Point(1, 2, -3), new Vector(0, -2, 0));
        // TC01: plane isn't parallel nor orthogonal to view plane - 9 intersections points
        assertEquals(9, countIntersectionsCameraGeometry(camera3, 3, 3,
                        new Plane(new Point(1, 2, -3), new Vector(0, -2, 0))),
                "wrong number of intersections points - regular plane should be 9");

        // TC02: plane parallel to view plane - 9 intersections points
        Plane plane1 = new Plane(new Point(1, 2, -3),
                new Vector(0, -2, 0));
        assertEquals(9, countIntersectionsCameraGeometry(camera3, 3, 3,
                        new Plane(new Point(1, 2, -3), new Vector(0, -2, 0))),
                "wrong number of intersections points - plane parallel to view plane should be 9");

        // TC03: third row of view plane doesn't intersect with plane - 6 intersections points
        Plane plane3 = new Plane(new Point(0, 2, 0), new Vector(0, -0.5, -1));
        assertEquals(6, countIntersectionsCameraGeometry(camera3, 3, 3,
                        new Plane(new Point(0, 2, 0), new Vector(0, -0.5, -1))),
                "wrong number of intersections points - intersect 2/3, should be 6");

    }

    /**
     * test number of intersections between rays from the view plane and a triangle
     */
    @Test
    void testIntersectionsRayTriangle() throws Exception {

        //TC01: triangle smaller/as size of center pixel of the view plane
        Triangle triangle1 = new Triangle(new Point(1, -1, -2),
                new Point(0, 1, -1),
                new Point(-1, -1, -2));
        assertEquals(1, countIntersectionsCameraGeometry(camera, 3, 3,
                        new Triangle(new Point(1, -1, -2),
                                new Point(0, 1, -1),
                                new Point(-1, -1, -2))),
                "wrong number of intersections points - " +
                        "triangle with size smaller then center pixel should be 1");

        // TC02: triangle intersect with two rays from the view plane
        Triangle triangle2 = new Triangle(new Point(0, 2, 20),
                new Point(-0.5, 1, -1),
                new Point(0.5, 1, -1));
        assertEquals(2, countIntersectionsCameraGeometry(camera3, 3, 3,
                        new Triangle(new Point(0, 2, 20),
                                new Point(-0.5, 1, -1),
                                new Point(0.5, 1, -1))),
                "wrong number of intersections points - triangle intersect with two rays from view plane" +
                        "should be 2");
    }


    private int countIntersectionsCameraGeometry(Camera camera, int Nx, int Ny, Intersectable geometry) { //throws Exception
        int count = 0;
        List<Point> intersections;

        for (int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                intersections = geometry.findIntersections(camera.constructRay(Nx, Ny, j, i));
                if (intersections != null) {
                    count += intersections.size();
                }
            }
        }
        return count; //Return the number of points of intersection between the geometries and a ray from the camera.
    }


}
