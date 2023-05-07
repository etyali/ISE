package geometries;
import java.util.List;
import primitives.Point;
import primitives.Ray;

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
    public List<Point> findIntsersections(Ray ray){return null;}
}
