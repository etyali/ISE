package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Geometries extends Intersectable {
    /**
     * list of geometries in scene
     */
    List<Intersectable> geometries;

    /**
     * default constructor - initialize geometries list with empty list
     */
    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    /**
     * constructor - initialize geometries list
     *
     * @param geometries new geometries list
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        add(geometries);
    }


    /**
     * add list of geometries to geometries list
     *
     * @param geometries new geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    /**
     * find all intersections of ray and geometries in the list
     *
     * @param ray ray for intersections
     * @return list of all geo intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (geometries == null) return null;
        List<GeoPoint> allIntersections = new LinkedList<>();
        List<GeoPoint> temp;
        for (Intersectable i : geometries) {
            temp = i.findGeoIntersections(ray, maxDistance);
            if (temp != null) allIntersections.addAll(temp);
        }
        if (allIntersections.isEmpty()) return null;
        return allIntersections;
    }
}
