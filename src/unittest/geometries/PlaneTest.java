package unittest.geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import geometries.Plane;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class PlaneTest {

    Plane p = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

    PlaneTest() throws Exception {
    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: A simple single test here (all the points are the same)
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), p.getNormal(), "Incorrect normal to plane");

    }

    public void testFindIntersaction() throws Exception {
        Plane plane = new Plane(new Point(-3, -3, 1), new Point(3, -3, 1),
                new Point(1, 5, 1));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line intersect the plane (1 intersection)
        List<Point> result1 = plane.findIntersections(new Ray(new Point(1, 3, 0),
                new Vector(0, 0, 2)));
        assertEquals(1, result1.size(), "Wrong number of points")

        // TC01: Ray's line does not intersect the plane (0 intersection)
        assertNull(plane.findIntersections(new Ray(new Point(-0.81838, 0.48601, 0), new Vector(1.82, 1.6, 1))),
                "Ray's line out of cylinder");

    }

}