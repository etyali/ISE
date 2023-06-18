package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Geometries extends Intersectable {
    /**
     * list of geometries in scene
     */
    List<Intersectable> geometriesList;

    /**
     * default constructor - initialize geometries list with empty list
     */
    public Geometries() {
        this.geometriesList = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
    }

    //    this.geometriesList = geometries;
    //}

    /**
     * constructor - initialize geometries list
     *
     * @param geometriesList new geometries list
     */
    public Geometries(List<Intersectable> geometriesList) {
        this.geometriesList = geometriesList;
    }

    /**
     * add list of geometries to geometries list
     *
     * @param geometries new geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(geometriesList, geometries);
    }


    public List<Point> findIntersections(Ray ray) {
        if (geometriesList == null) return null;
        List<Point> allIntersections = new LinkedList<>();
        List<Point> temp;
        for (Intersectable i : geometriesList) {
            temp = i.findIntersections(ray);
            if (temp != null) allIntersections.addAll(temp);
        }
        if (allIntersections.isEmpty()) return null;
        return allIntersections;
    }

    /**
     * find all intersections of ray and geometries in the list
     *
     * @param ray ray for intersections
     * @return list of all geo intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (geometriesList == null) return null;
        List<GeoPoint> allIntersections = new LinkedList<>();
        List<GeoPoint> temp;
        for (Intersectable i : geometriesList) {
            temp = i.findGeoIntersections(ray, maxDistance);
            if (temp != null) allIntersections.addAll(temp);
        }
        if (allIntersections.isEmpty()) return null;
        return allIntersections;
    }
}
