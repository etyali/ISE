package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Geometries extends Intersectable {
    List<Intersectable> geometriesList;

    public Geometries() {
        this.geometriesList = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
    }

    //    this.geometriesList = geometries;
    //}
    public Geometries(List<Intersectable> geometriesList) {
        this.geometriesList = geometriesList;
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(geometriesList, geometries);
    }


    /*public List<Point> findIntersections(Ray ray) {
        return null;
    }*/

    /**
     * find all intersections of ray and geometries in the list
     *
     * @param ray ray for intersections
     * @return list of all geo intersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (geometriesList == null) return null;
        List<GeoPoint> allIntersections = new LinkedList<>();
        List<GeoPoint> temp;
        for (Intersectable i : geometriesList) {
            temp = i.findGeoIntersections(ray);
            if (temp != null) allIntersections.addAll(temp);
        }
        if (allIntersections.isEmpty()) return null;
        return allIntersections;
    }
}
