package unittest.renderer;

import geometries.Intersectable;
import geometries.Sphere;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class CameraIntegrationsTest {

    static final Point ZERO_POINT = new Point(0, 0, 0);

    @Test
    void testIntersectionsRaySphere() {
        //Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(3d);

        Camera camera = new Camera(new Point(0, 0, 5), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(3d, 3d);
        camera.setVPDistance(3d);

        //group 1: sphere is visible and out of view plane
        //TC01: sphere behind view plane
        assertEquals(2,countIntersectionsCameraGeometry(camera, 3d,3d,new Sphere(0.5, (0.5,0.5, ))));
        //TC02: sphere in front of the view plane

        //group 2: view plane is in the sphere

    }
    @Test
    void testIntersectionsRaySPlane() {
        Plane plane = new plane();

    }
    private int countIntersectionsCameraGeometry(Camera camera, int Nx, int Ny, Intersectable geometry) throws Exception {
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
