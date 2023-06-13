package geometries;

public abstract class RadialGeometry extends Geometry {
    protected final double radius;

    /**
     * Radial Geometries constructor
     *
     * @param radius will be the radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
