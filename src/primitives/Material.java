package primitives;

public class Material {
    public Double3 kD = Double3.ZERO, kS = Double3.ZERO;

    public int nShininess = 0;

    /**
     * @param kD
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * @param kD
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * @param ks
     * @return
     */
    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }

    /**
     * @param ks
     * @return
     */
    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    /**
     * @param nShininess
     * @return
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
