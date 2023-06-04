package unittest.renderer;

import geometries.Intersectable;
import primitives.Point;
import renderer.Camera;

import java.util.List;

public class CameraIntegrationsTest {


    private int countIntersectionsCameraGeometry(Camera camera, int Nx, int Ny, Intersectable geometry) throws Exception {
        int count = 0;
        List<Point> intersections;

        for (int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                intersections = geometry.findIntersections(camera.constructRay(Nx, Ny, j, i));
                if (intersections != null)
                {
                    count += intersections.size();
                }
            }
        }
        return count; //Return the number of points of intersection between the geometries and a ray from the camera.
    }



}
