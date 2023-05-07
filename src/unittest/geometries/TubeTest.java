package unittest.geometries;

import geometries.Sphere;
import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() throws Exception {

        Tube t = new Tube(2, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));

        // ============ Equivalence Partitions Tests ==============
        // TC01: A simple single test here (all the points are the same)
        assertEquals(t.getNormal(new Point(2, 0, 1)),new Vector(1,0,0), "Incorrect normal to tube");

        // =============== Boundary Values Tests ==================
        // TC02: Normal is orthogonal to the head of the axis Ray
        assertEquals(t.getNormal(new Point(2, 0, 0)),new Vector(1,0,0), "Incorrect normal to tube");
    }
}