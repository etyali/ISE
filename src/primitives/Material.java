package primitives;

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
     * setters
     */
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
}
