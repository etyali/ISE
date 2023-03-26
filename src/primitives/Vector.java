package primitives;

public class Vector extends Point {
    /**
     * Constructor to initialize point based object the same number values
     *
     * @param xyz number value for the point
     */
    Vector(Double3 xyz) throws Exception {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("can not construct the zero vector");
        }
    }

    /**
     * Constructor to initialize vector based object the same number values
     *
     * @param x first number value for the vector's point
     * @param y second number value for the vector point
     * @param z third number value for the vector point
     */
    public Vector(double x, double y, double z) throws Exception {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("can not construct the zero vector");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector other) {
            return super.equals(other);
        }
        return false;
    }

    /**
     * return new vector-our vector+v
     *
     * @param v vector to add
     */
    public Vector add(Vector v) /*throws Exception*/ {
        try {
            return new Vector(xyz.add(v.xyz));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    } /**/

    /**
     * return new vector-our vector with scalar multiplication
     *
     * @param d scalar for multiplication
     */
    public Vector scale(double d) throws Exception {
        try {
            return new Vector(xyz.scale(d));
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * return cross product between our vector and vector v (linear)
     *
     * @param v vector for cross product
     */
    public Vector crossProduct(Vector v) throws Exception {
        try {
            return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2, xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                    xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * return length of v, squared
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * return length of v
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * return normalize vector of our vector
     */
    public Vector normalize() {
        try {
            double len = length();
            return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * return dot product between our vector and vector other (linear)
     *
     * @param other vector for dot product
     */
    public double dotProduct(Vector other) {
        return xyz.d1 * other.xyz.d1 + xyz.d2 * other.xyz.d2 + xyz.d3 * other.xyz.d3;
    }

}
