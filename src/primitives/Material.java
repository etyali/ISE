package primitives;

/**
 * Material of geometry class
 *
 * @author Etya Lichtman and Orly Salem
 */
public class Material {
    /**
     * diffusive and specular
     */
    public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
    /**
     * material's shininess
     */
    public int nShininess = 0;
    /**
     * transparency and reflection attenuation
     */
    public Double3 kT = Double3.ZERO, kR = Double3.ZERO;

    //----------------------------SETTERS-------------------------------

    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }

    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setkT(double kt) {
        this.kT = new Double3(kt);
        return this;
    }

    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    public Material setkR(double kr) {
        this.kR = new Double3(kr);
        return this;
    }
}
