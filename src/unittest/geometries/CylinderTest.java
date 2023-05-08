package unittest.geometries;

import java.util.*;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() throws Exception {
        Cylinder c = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2, 2);

        // ============ Equivalence Partitions Tests ==============

        // TC01: normal for a point on the casing of the cylinder
        assertEquals(c.getNormal(new Point(2, 0, 1)), new Vector(1, 0, 0),
                "Incorrect normal for a point on the casing of the cylinder");

        // TC02: normal for a point on the base of the cylinder where the axis ray starts
        assertEquals(c.getNormal(new Point(1, 1, 0)), new Vector(0, 0, 1),
                "Incorrect normal for a point on the base of the cylinder where the axis ray starts");

        // TC03: normal for a point on the parallel base of the cylinder where the axis ray starts
        assertEquals(c.getNormal(new Point(1, 1, 2)), new Vector(0, 0, 1),
                "Bad normal for a point on the parallel base of the cylinder where the axis ray starts");

        // =============== Boundary Values Tests ==================

        //TC01: test for normal to point on the base edge
        assertEquals(c.getNormal(new Point(2, 0, 2)), new Vector(0, 0, 1),
                "Incorrect normal for a point on the base edge of the cylinder");

        //TC02: test for normal to point on the other base edge
        assertEquals(c.getNormal(new Point(2, 0, 0)), new Vector(0, 0, 1),
                "Incorrect normal for a point on the base edge of the cylinder");

        //TC03: test for normal to center point on the base edge of the cylinder
        assertEquals(c.getNormal(new Point(0, 0, 0)), new Vector(0, 0, 1),
                "Incorrect normal for a center point on the base edge of the cylinder");

        //TC04: test for normal to point on the other base edge of the cylinder
        assertEquals(c.getNormal(new Point(0, 0, 2)), new Vector(0, 0, 1),
                "Incorrect normal for a center point on the base of the cylinder");
    }
/*
    public void testFindIntersaction() {
        Cylinder c = new Cylinder(new Ray(new Point(1, 0, 0), new Vector(0, 0, 1)), 1, 1);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the cylinder (zero intersections)
        assertNull(c.findIntersections(new Ray(new Point(-0.81838, 0.48601, 0), new Vector(1.82, 1.6, 1))),
                "Ray's line out of cylinder");
        // TC02: Ray starts inside the cylinder (1 intersection)
        List<Point> result1 = c.findIntersections(new Ray(new Point(0.81838, 0.48601, 0.56951),
                new Vector(0.18, 1.6, 0.43)));
        assertEquals(1, result1.size(), "Wrong number of points");
        // TC03: Ray starts before and crosses the cylinder (2 intersections)
        List<Point> result2 = c.findIntersections(new Ray(new Point(0.81838, -0.48601, -0.32453),
                new Vector(0.18, 2.57, 1.44)));
        assertEquals(2, result2.size(), "Wrong number of points");

        // TC04: Ray starts after the cylinder (0 intersections)
        assertNull(c.findIntersections(new Ray(new Point(1, 1, 1.5), new Vector(0, 1.09, 0.5))),
                "Ray's line out of cylinder");

        // =============== Boundary Values Tests ==================
        }
*/


}