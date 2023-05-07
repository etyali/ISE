package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries {
    List<Intersectable> geometriesList;

    public Geometries() {
        this.geometriesList = new ArrayList<>();
    } //null;  רשימה ריקה?

    public Geometries(Intersectable... geometries) {
    }

    //    this.geometriesList = geometries;
    //}
    public Geometries(List<Intersectable> geometriesList) {
        this.geometriesList = geometriesList;
    }

    public void add(Intersectable... geometries) {
    }


    public List<Point> findIntsersections(Ray ray) {
        return null;
    }

}
