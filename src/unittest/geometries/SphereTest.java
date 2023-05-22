package unittest.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SphereTest {
    Sphere sphere = new Sphere(1d, new Point (1, 0, 0));  // for intersections test
    Sphere s = new Sphere(2, new Point(0,0,0));
    @Test
    void testGetNormal() throws Exception {
        // ============ Equivalence Partitions Tests ==============

        // TC01: A simple single test here (all the points are the same)
        assertEquals(new Vector(0,0,1), s.getNormal(new Point(0,0,2)),"Incorrect normal to sphere");

    }

    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray starts outside the sphere (0 point)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 0, -1))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)

        // TC03: Ray starts inside the sphere (1 point)

        // TC04: Ray starts after the sphere (0 points)



        //assertEquals(new Point(1,0,1),s.findIntersections(new Ray(new Point(0.5,0,0), new Vector(1,0,1))), "Wrong point result of intersection, ray starts inside the sphere (1 point)");



    }
}