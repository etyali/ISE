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
    //private static final double DELTA = 0.1;

    private static final double INITIAL_K = 1.0;

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
        //Vector epsVector;
        //if (nv < 0) epsVector = n.scale(DELTA);
        //else epsVector = n.scale(-DELTA);
        //Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));//,
        return intersections == null;

    }

    /**
     * calculate transparency of intersection
     *
     * @param geoPoint intersection point on geometry
     * @param ls       light source
     * @param l        light source vector
     * @param n        normal to intersection point
     * @return ktr of geoPoint
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Double3 ktr = Double3.ONE;
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(geoPoint.point));//,
        if (intersections == null) return ktr;
        for (GeoPoint intersection : intersections) {
            if (ktr.equals(Double3.ZERO)) return Double3.ZERO;
            ktr = ktr.product(intersection.geometry.getMaterial().kT);
        }
        return ktr;
    }

    /**
     * find the closest intersection between ray and scene geometries
     *
     * @param ray for intersection
     * @return closest intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * trace ray
     *
     * @param ray parameter for function
     */
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
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
        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate color according to level and attenuation
     *
     * @param intersection geometry and point
     * @param ray          intersect with intersection
     * @param level        depth of calculate color
     * @param k            calc local effect parameter
     * @return color according to intersections
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, INITIAL_K));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calculate local effect on color
     *
     * @param intersection intersection point
     * @param ray          intersection ray
     * @param k            for calculating
     * @return color with local effects
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
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
                Double3 ktr = transparency(intersection, lightSource, l, n);
                if (!ktr.scale(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)),
                            lightIntensity.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;

    }

    /**
     * calculate global effect on color
     *
     * @param intersection geometry and point
     * @param ray          intersect with intersections geometry
     * @param level        depth of calculating
     * @param k            for calculating
     * @return color with global effects
     */
    private Color calcGlobalEffects(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = intersection.geometry.getNormal(intersection.point);
        Ray reflectedRay = constructReflectedRay(n, intersection.point, ray.getDir());
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        Ray refractedRay = constructRefractedRay(n, intersection.point, ray.getDir());
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        Double3 kr = intersection.geometry.getMaterial().kR, kkr = k.product(kr);
        Double3 kt = intersection.geometry.getMaterial().kT, kkt = k.product(kt);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            if (reflectedPoint == null) color = scene.background;
            else color = calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr);
        }
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            if (refractedPoint == null) color.add(scene.background);
            else color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
        //return intersection == null ? scene.background : calcColor(intersection, ray, level - 1, )
    }

    /**
     * calculate reflected ray
     *
     * @param n     normal to geopoint geometry
     * @param point geopoint point
     * @param v     ray direction
     * @return reflrcted ray
     */
    private Ray constructReflectedRay(Vector n, Point point, Vector v) {
        if (Util.isZero(v.dotProduct(n))) return new Ray(point, v);
        Vector r = v.subtract(n.scale(v.dotProduct(n)).scale(2)); //𝒓 = 𝒗 − 𝟐 ∙ 𝒗 ∙ 𝒏 ∙ 𝒏
        return new Ray(point, r, n);
    }

    /**
     * calculate refracted ray
     *
     * @param n     normal to geopoint geometry
     * @param point geopoint point
     * @param v     ray direction
     * @return refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point point, Vector v) {
        return new Ray(point, v, n);
    }


    /**
     * calculate color diffusive
     *
     * @param material geometry material
     * @param nl       n dot product light source vector
     * @return Double3
     */
    public Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * calculate color specular
     *
     * @param material geometry material
     * @param n        normal
     * @param l        light vector
     * @param nl       n dot product l
     * @param v        ray direction vector
     * @return vector specular
     */
    public Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl).scale(2)); // r = l - 2(n * l) * n
        return material.kS.scale(Math.pow(Math.max(0d, r.dotProduct(v.scale(-1))), material.nShininess));
    }

}
