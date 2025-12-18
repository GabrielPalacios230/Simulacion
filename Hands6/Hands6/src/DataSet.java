public class DataSet {
    private double[] x1; // Advertising
    private double[] x2; // Index artificial (1..n)
    private double[] y;  // Sales (yield)

    public DataSet() {
        // Benetton dataset (Advertising -> Sales)
        this.x1 = new double[] {23, 26, 30, 34, 43, 48, 52, 57, 58};
        this.y  = new double[] {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

        // x2: índice artificial para convertir a regresión múltiple
        this.x2 = new double[x1.length];
        for (int i = 0; i < x1.length; i++) {
            this.x2[i] = i + 1; // 1,2,3,...,9
        }
    }

    public double[] getX1() {
        return x1;
    }

    public double[] getX2() {
        return x2;
    }

    public double[] getY() {
        return y;
    }

    public int size() {
        return y.length;
    }
}
