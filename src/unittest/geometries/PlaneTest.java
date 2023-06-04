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

        //TC01: Ray intersects the plane
        assertEquals(List.of(new Point(1,0.5,1)),
                plane.findIntersections(new Ray(new Point(0,0.5,0),new Vector(1,0,1))),
                "Ray intersects the plane");

        //TC02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,0.5,2), new Vector(1,2,5))),
                "Ray does not intersect the plane");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray is parallel to the plane
        //TC10: The ray included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,1), new Vector(1,0,0))),
                "Ray is parallel to the plane, the ray included in the plane");

        //TC11: The ray not included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,2), new Vector(1,0,0))),
                "Ray is parallel to the plane, the ray not included in the plane");

        // **** Group: Ray is orthogonal to the plane
        //TC12: according to p0, before the plane
        assertEquals(List.of(new Point(1,1,1)),
                plane.findIntersections(new Ray(new Point(1,1,0), new Vector(0,0,1))),
                "Ray is orthogonal to the plane, according to p0, before the plane");

        //TC13: according to p0, in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,1), new Vector(0,0,1))),
                "Ray is orthogonal to the plane, according to p0, in the plane");

        //TC14: according to p0, after the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,2), new Vector(0,0,1))),
                "Ray is orthogonal to the plane, according to p0, after the plane");

        // **** Group: Ray is neither orthogonal nor parallel to
        //TC15: Ray begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(2,4,1), new Vector(2,3,5))),
                "Ray is neither orthogonal nor parallel to ray and begin at the plane");

        //TC16: Ray begins in the same point which appears as reference point in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,0,1), new Vector(2,3,5))),
                "Ray is neither orthogonal nor parallel to ray and begins in the same point " +
                        "which appears as reference point in the plane");

    }

}