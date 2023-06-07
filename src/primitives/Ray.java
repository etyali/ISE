package primitives;

import java.util.*;

public class Ray {
    public Point p0;
    public Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Get the start point of the ray
     *
     * @return P0 - the start point of ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get the direction of the ray
     *
     * @return The direction's vector
     */
    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) throws IllegalArgumentException {
            return p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
