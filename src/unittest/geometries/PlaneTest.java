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

    @Test
    public void testFindIntsersections() throws Exception {
        Plane plane = new Plane(new Point(-3, -3, 1), new Point(3, -3, 1),
                new Point(1, 5, 1));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line intersect the plane (1 intersection)
        List<Point> result1 = plane.findIntersections(new Ray(new Point(1, 3, 0),
                new Vector(0, 1, 2)));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(new Point(1, 3.5, 1), result1.get(0));

        // TC02: Ray's line does not intersect the plane (0 intersection)
        assertNull(plane.findIntersections(new Ray(new Point(-1, 0.5, 2), new Vector(1.82, 1.6, 1))),
                "Ray's line out of plane");

        // =============== Boundary Values Tests ==================

        //TC03: Ray's parallel to plane (not included)
        assertNull(plane.findIntersections(new Ray(new Point(10, 4.5, 2), new Vector(-5, -7, 0))),
                "Ray's line out of plane- parallel");

        //TC04: Ray's parallel to plane (included) - return null
        assertNull(plane.findIntersections(new Ray(new Point(10, 4.5, 1), new Vector(-5, -7, 0))),
                "Ray's line included in plane");

        //TC05: Ray's orthogonal to plane - start before (1 intersect)
        List<Point> result2 = plane.findIntersections(new Ray(new Point(1, 3, 0),
                new Vector(0, 0, 2)));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(new Point(1, 3, 1), result2.get(0));

        //TC06: Ray's orthogonal to plane - start point after plane (0 intersect)
        assertNull(plane.findIntersections(new Ray(new Point(1, 3, 3), new Vector(0, 0, 2))),
                "Ray's line out of plane- orthogonal");

        //TC07: Ray's orthogonal to plane - start point included in plane (1 intersect)
        assertNull(plane.findIntersections(new Ray(new Point(2, 4, 1), new Vector(0, 0, 1))),
                "Ray's line start in plane - should be null");

        //TC08: Ray's start in plane (not orthogonal or parallel)
        assertNull(plane.findIntersections(new Ray(new Point(2, 4, 1), new Vector(0, 1, 1))),
                "Ray's line start in plane - should be null");

        //TC09: Ray's start in one of plane's points (not orthogonal or parallel)
        assertNull(plane.findIntersections(new Ray(new Point(-3, -3, 1), new Vector(0, 1, 1))),
                "Ray's line start in plane - should be null");
    }

}