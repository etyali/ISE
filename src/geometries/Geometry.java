package geometries;

import primitives.Material;
import primitives.Point;
import primitives.Vector;
import primitives.Color;

import java.awt.*;

public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * to do in implements classes
     */
    public abstract Vector getNormal(Point p) throws IllegalArgumentException;

    /**
     * return geometry's emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * @return Geometry material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * set new color for emission
     *
     * @param emission new color for emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @param material new Geometry material
     * @return Geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
