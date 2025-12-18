public class MultipleLinearRegression {
    private double[][] X;
    private double[] Y;
    private double b0, b1, b2;

    // Constructor que recibe el dataset
    public MultipleLinearRegression(double[][] X, double[] Y) {
        this.X = X;
        this.Y = Y;
    }

    // Método para ajustar el modelo (calcular coeficientes)
    public void fit() {
        int n = X.length;
        double sumX1 = 0, sumX2 = 0, sumY = 0;

        for (int i = 0; i < n; i++) {
            sumX1 += X[i][0];
            sumX2 += X[i][1];
            sumY += Y[i];
        }

        double meanX1 = sumX1 / n;
        double meanX2 = sumX2 / n;
        double meanY = sumY / n;

        double sXX1 = 0, sXX2 = 0, sX1X2 = 0, sX1Y = 0, sX2Y = 0;

        for (int i = 0; i < n; i++) {
            double dx1 = X[i][0] - meanX1;
            double dx2 = X[i][1] - meanX2;
            double dy = Y[i] - meanY;

            sXX1 += dx1 * dx1;
            sXX2 += dx2 * dx2;
            sX1X2 += dx1 * dx2;
            sX1Y += dx1 * dy;
            sX2Y += dx2 * dy;
        }

        double denominator = (sXX1 * sXX2) - (sX1X2 * sX1X2);
        b1 = ((sX1Y * sXX2) - (sX2Y * sX1X2)) / denominator;
        b2 = ((sX2Y * sXX1) - (sX1Y * sX1X2)) / denominator;
        b0 = meanY - b1 * meanX1 - b2 * meanX2;
    }

    // Imprimir la ecuación del modelo
    public void printEquation() {
        System.out.println("-------------------------------------");
        System.out.println("      Regresion Lineal Multiple");
        System.out.println("--------------------------------------");
        System.out.printf("Ecuación: Y = %.4f + %.4f*X1 + %.4f*X2%n", b0, b1, b2);
        System.out.println("---------------------------------------");
    }

    // Predecir el valor de Y a partir de X1 y X2
    public double predict(double x1, double x2) {
        return b0 + b1 * x1 + b2 * x2;
    }

    // Calcular el coeficiente de determinación (R²)
    public double calculateR2() {
        double ssTot = 0, ssRes = 0;
        double meanY = 0;
        int n = Y.length;

        for (double val : Y) {
            meanY += val;
        }
        meanY /= n;

        for (int i = 0; i < n; i++) {
            double yPred = predict(X[i][0], X[i][1]);
            ssRes += Math.pow(Y[i] - yPred, 2);
            ssTot += Math.pow(Y[i] - meanY, 2);
        }

        return 1 - (ssRes / ssTot);
    }
}
