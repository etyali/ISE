package primitives;

import java.util.Objects;
import java.math.*;

/**
 * Point class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class Point {
    /**
     * point coordinate
     */
    final public Double3 xyz;

    /**
     * Constructor to initialize point based object the same number values
     *
     * @param xyz number value for the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Constructor to initialize point based object the same number values
     *
     * @param x first number value for the point
     * @param y second number value for the point
     * @param z third number value for the point
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point other)
            return xyz.equals(other.xyz);
        return false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * add vector to point and return new point
     *
     * @param v direction vector to move the point
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * receives a Point as a parameter, returns a vector from second point to the point which called the method
     *
     * @param p the tail of the vector (xyz is the head of the vector)
     */
    public Vector subtract(Point p) throws IllegalArgumentException {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * calculate distance squared between two points
     *
     * @param p the other point to calculate distance
     *          this: the first point to calculate distance
     * @return the distance between two points, squared
     */
    public double distanceSquared(Point p) {
        return ((this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1)
                + (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2)
                + (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3));
    }

    /**
     * calculate the distance between two points
     *
     * @param p the other point to calculate distance
     *          this: the first point to calculate distance
     * @return the distance between two points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    public double getX() {
        return xyz.d1;
    }
}
