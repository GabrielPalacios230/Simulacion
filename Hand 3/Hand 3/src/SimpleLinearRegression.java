/**
####################################################################
                            Descripción:
 o Esta clase implementa el modelo matemático de Regresión Lineal
 o Simple (SLR) orientado a objetos, utilizando los datos del caso
 o Benetton. Calcula los coeficientes B0 (intercepto), B1 (pendiente)
 o y el coeficiente de determinación R².
#####################################################################
 */

public class SimpleLinearRegression {
    private double[] x; // Variable independiente (Advertising)
    private double[] y; // Variable dependiente (Sales)
    private double b0;  // Intercepto
    private double b1;  // Pendiente
    private double r2;  // Coeficiente de determinación

    /**
     * Constructor que recibe los datos de entrada y calcula los parámetros.
     */
    public SimpleLinearRegression(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        calcularCoeficientes();
        calcularR2();
    }

    /**
     * Metodo para calcular los coeficientes B0 y B1
     * según las fórmulas de regresión lineal simple.
     */
    private void calcularCoeficientes() {
        int n = x.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
        }

        double meanX = sumX / n;
        double meanY = sumY / n;

        b1 = (sumXY - n * meanX * meanY) / (sumX2 - n * meanX * meanX);
        b0 = meanY - b1 * meanX;
    }

    /**
     * Cálculo del coeficiente de determinación R²
     * para medir la calidad del modelo.
     */
    private void calcularR2() {
        double ssTot = 0;
        double ssRes = 0;
        double meanY = promedio(y);

        for (int i = 0; i < y.length; i++) {
            double yPred = predecir(x[i]);
            ssRes += Math.pow(y[i] - yPred, 2);
            ssTot += Math.pow(y[i] - meanY, 2);
        }

        r2 = 1 - (ssRes / ssTot);
    }

    /**
     * Metodo para predecir un valor de Y a partir de un valor X.
     */
    public double predecir(double valorX) {
        return b0 + b1 * valorX;
    }

    /**
     * Muestra en consola la ecuación del modelo y el R².
     */
    public void mostrarResultados() {
        System.out.println("-------- Modelo de Regresión Lineal Simple --------");
        System.out.println("Ecuación del modelo:");
        System.out.println("Sales = " + String.format("%.4f", b0) + " + " + String.format("%.4f", b1) + " * Advertising");
        System.out.println("Coeficiente de determinación (R²): " + String.format("%.4f", r2));
        System.out.println("--------------------------------------");
    }

    // Metodo auxiliar para calcular el promedio
    private double promedio(double[] datos) {
        double sum = 0;
        for (double d : datos) sum += d;
        return sum / datos.length;
    }
}
