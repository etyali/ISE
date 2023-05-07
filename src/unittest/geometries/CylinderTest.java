package unittest.geometries;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() throws Exception {
        Cylinder c = new Cylinder(new Ray(new Point(0,0,0), new Vector(0,0,1)),2,2);

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
}