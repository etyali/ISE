package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import primitives.Util;
import lighting.LightSource;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    /**
     * constructor - calls father's constructor in RayTracerBase
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * trace ray
     *
     * @param ray parameter for function
     */
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        //List<GeoPoint> allGPoints = scene.geometries.findGeoIntersections(ray);
        if (closestPoint == null) {
            return scene.background;
        }
        return calcColor(closestPoint, ray);
    }

    /**
     * @param point
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        //return scene.geometries.
        //return (new Color(java.awt.Color.GREEN));
        //System.out.println(point.geometry.getEmission());
        //return point.geometry.getEmission().add(scene.ambientLight.getIntensity());
        //Color color = point.geometry.getEmission().add(scene.ambientLight.getIntensity());
        Color color = scene.ambientLight.getIntensity().add(calcLocalEffects(point, ray));
        return color;
    }

    /**
     * @param intersection
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = intersection.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = intersection.geometry.getMaterial();
        //int nShininess = intersection.geometry.getMaterial().nShininess;
        //double kd = intersection.geometry.getMaterial().kD, ks = intersection.geometry.getMaterial().kS();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(lightIntensity.scale(calcDiffusive(material, nl)),
                        lightIntensity.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;

    }

    /**
     * @param material
     * @param nl
     * @return
     */
    public Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return
     */
    public Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl).scale(2)); // r = l - 2(n * l) * n
        return material.kS.scale(Math.pow(Math.max(0d, r.dotProduct(v.scale(-1))), material.nShininess));
    }

}
