package unittest.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PointTest {

    Point p1 = new Point(1, 2, 3);

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct addition between point and vector
        assertEquals (p1.add(new Vector(-1, -2, -3)),
                new Point(0, 0, 0),
                "ERROR - Point add Vector");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct subtraction between 2 points
        assertEquals(new Vector(1, 1, 1),
                new Point(2, 3, 4).subtract(p1),
                "ERROR - subtract two points");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: a regular distance squared
        assertEquals(p1.distanceSquared(new Point(3, 0,0)), 17,
                "wrong calculation of distanceSquared() in Point");

        // TC02: Same points
        assertTrue(isZero(p1.distanceSquared(new Point(1, 2, 3))),
                "wrong calculation of distanceSquared() for same points");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: a regular distance
        assertEquals(p1.distance(new Point(3, 0,0)), Math.sqrt(17),
                "wrong calculation of distance() in Point");

        // TC02: Co-located points
        assertTrue(isZero(new Point(1, 2, 3).distance(new Point(1, 2, 3))),
                "wrong calculation of distanceSquared() for co-located points");
    }
}