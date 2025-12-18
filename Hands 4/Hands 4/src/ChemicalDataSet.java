public class ChemicalDataSet {
    private final double[][] x;  // Variables independientes (x1, x2)
    private final double[] y;    // Variable dependiente (yield)

    public ChemicalDataSet() {
        // Ejemplo: dataset de 17 experimentos (puedes reemplazar con tus datos reales)
        x = new double[][]{
                {0.5, 1.0}, {1.0, 2.0}, {1.5, 2.5}, {2.0, 3.0}, {2.5, 3.5},
                {3.0, 4.0}, {3.5, 4.5}, {4.0, 5.0}, {4.5, 5.5}, {5.0, 6.0},
                {5.5, 6.5}, {6.0, 7.0}, {6.5, 7.5}, {7.0, 8.0}, {7.5, 8.5},
                {8.0, 9.0}, {8.5, 9.5}
        };

        y = new double[]{
                5.0, 6.5, 7.0, 8.2, 9.1,
                10.5, 11.0, 12.3, 13.5, 14.0,
                15.2, 16.0, 17.1, 18.0, 19.2,
                20.0, 21.0
        };
    }

    public double[][] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }
}
