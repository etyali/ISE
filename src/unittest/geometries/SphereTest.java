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

    Sphere s = new Sphere(2, new Point(0,0,0));
    @Test
    void testGetNormal() throws Exception {
        // ============ Equivalence Partitions Tests ==============

        // TC01: A simple single test here (all the points are the same)
        assertEquals(new Vector(0,0,1), s.getNormal(new Point(0,0,2)),"Incorrect normal to sphere");

    }

    @Test
    void testFindIntsersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray starts inside the sphere (1 point)

        assertEquals(new Point(1.5,0.98,0.89),s.findIntsersections(new Ray(new Point(1,0,1), new Vector(0.5,0.98,-0.11))), "Wrong point result of intersection, ray starts inside the sphere (1 point)");

        //TC02: Ray


    }
}