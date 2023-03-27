package geometries;

import primitives.Point;

public class Triangle extends Polygon {
    /**
     * Triangle constructor
     *
     * @param vertices will be the list of 3 vertices
     */
    public Triangle(Point... vertices) throws Exception {
        super(vertices);
        if (vertices.length != 3) {
            throw new IllegalArgumentException("A Triangle can't have other than 3 vertices");
        }
    }
}
