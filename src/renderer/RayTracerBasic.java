package renderer;

import lighting.PointLight;
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
     * DELTA for calculating
     */
    private static final double DELTA = 0.1;

    private static final double INITIAL_K = 1.0;

    /**
     * check if there is something between geometry and light
     *
     * @param gp    the geometry that the point is on
     * @param light light source
     * @param l     vector from light to point
     * @param n     normal to point
     * @param nv    dot product between normal to point(n) and ray's direction vector(v)
     * @return true if it's unshaded
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector;
        if (nv < 0) epsVector = n.scale(DELTA);
        else epsVector = n.scale(-DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));//,
        return intersections == null;

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
     * calculate color
     *
     * @param point geometry and point
     * @param ray   intersect with point
     * @return color
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        Color color = scene.ambientLight.getIntensity().add(calcLocalEffects(point, ray));
        return color;
    }

    /*private Color calcColor(GeoPoint point, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(point, ray);
        return color.add(calcGlobalEffects(point, ray.getDir(), level, INITIAL_K));
        //return 1 == level ? color : color.add(calcGlobalEffects(point, ray.getDir(), level, k));
    }*/

    /**
     * calculate local effect on color
     *
     * @param intersection intersection point
     * @param ray          intersection ray
     * @return color with local effect
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
                if (unshaded(intersection, lightSource, l, n, nv)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)),
                            lightIntensity.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;

    }

    //private Color calcGlobalEffects(GeoPoint intersection, Ray ray) {    }


    /**
     * calculate color diffusive
     *
     * @param material geometry material
     * @param nl
     * @return Double3
     */
    public Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calculate color diffusive
     *
     * @param material geometry material
     * @param n        normal
     * @param l        light vector
     * @param nl
     * @param v
     * @return vector specular
     */
    public Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl).scale(2)); // r = l - 2(n * l) * n
        return material.kS.scale(Math.pow(Math.max(0d, r.dotProduct(v.scale(-1))), material.nShininess));
    }

}
