/**
 ###############################################################
 o Universidad: Tecnologico Superior de Jalisco - Mario Molina
 o Materia: Análisis Predictivo y Modelos Matemáticos
 o Práctica: Simulation through MLR
 o Fecha: 18 de Diciembre de 2025
 o Autor: Gabriel Palacios Lorenzana
 ############################################################
 */
public class Main {

    public static void main(String[] args) {
        // ===========================================
        // 1️⃣ Crear el dataset de 17 Chemical Experiments
        // ===========================================
        double[][] X = {
                {41.9, 29.1},
                {43.4, 29.3},
                {43.9, 29.5},
                {44.5, 29.7},
                {47.3, 29.9},
                {47.5, 30.3},
                {47.9, 30.5},
                {50.2, 30.7},
                {52.8, 30.8},
                {53.2, 30.9},
                {56.7, 31.5},
                {57.0, 31.7},
                {63.5, 31.9},
                {65.3, 32.0},
                {71.1, 32.1},
                {77.0, 32.5},
                {77.8, 32.9}
        };

        double[] Y = {
                251.3, 251.3, 248.3, 267.5, 273.0, 276.5, 270.3,
                274.9, 285.0, 290.0, 297.0, 302.5, 304.5,
                309.3, 321.7, 330.7, 349.0
        };

        // ===========================================
        // 2️⃣ Crear e inicializar el modelo de regresión
        // ===========================================
        MultipleLinearRegression model = new MultipleLinearRegression(X, Y);

        // ===========================================
        // 3️⃣ Calcular coeficientes del modelo
        // ===========================================
        model.fit();

        // ===========================================
        // 4️⃣ Imprimir ecuación y resultados
        // ===========================================
        model.printEquation();

        // ===========================================
        // 5️⃣ Simular 5 experimentos nuevos
        // ===========================================
        double[][] nuevos = {
                {50.0, 30.0},
                {55.0, 31.0},
                {60.0, 31.5},
                {70.0, 32.0},
                {75.0, 32.8}
        };

        System.out.println("\nSimulaciones de nuevos experimentos:");
        for (double[] p : nuevos) {
            double predY = model.predict(p[0], p[1]);
            System.out.printf("Para X1 = %.1f y X2 = %.1f → Predicción (Yield) = %.2f%n", p[0], p[1], predY);
        }

        // ===========================================
        // 6️⃣ Calcular y mostrar el coeficiente R²
        // ===========================================
        double r2 = model.calculateR2();
        System.out.printf("%nCoeficiente de determinación (R²): %.4f%n", r2);
    }
}
