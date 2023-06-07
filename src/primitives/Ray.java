package primitives;

import java.util.List;
import java.util.Objects;

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

    /**
     * Return the closest point for beginning of the ray from all intersection points
     *
     * @param points list of intersections
     * @return {@link Point}
     */
    public Point findClosestPoint(List<Point> points) {
        Point closet = null;

        if (!points.isEmpty()) {
            closet = points.get(0);

            for (int i = 1; i < points.size(); i++) {
                if (points.get(i).distance(this.p0) < closet.distance(this.p0)) {
                    closet = points.get(i);
                }
            }

        }
        return closet;

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
