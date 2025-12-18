import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // 1) Dataset hardcoded (Benetton)
        DataSet ds = new DataSet();

        // 2) Parámetros del AG (puedes ajustar si quieres)
        int populationSize = 80;
        int generations = 500;
        double crossoverRate = 0.8;
        double mutationRate = 0.12;
        int tournamentSize = 3;
        boolean elitism = true;

        System.out.println("------ Algoritmo Genético para Regresión Lineal Múltiple ------");
        System.out.println("Modelo: Sales = beta0 + beta1 * Advertising + beta2 * Index\n");

        GeneticAlgorithm ga = new GeneticAlgorithm(ds, populationSize, generations,
                crossoverRate, mutationRate,
                tournamentSize, elitism);

        // 3) Ejecutar AG
        Individual best = ga.run();

        System.out.println("\n*********RESULTADOS FINALES*******");
        double b0 = best.getGene(0);
        double b1 = best.getGene(1);
        double b2 = best.getGene(2);
        System.out.printf("Coeficientes óptimos (por AG):\n beta0 = %.6f\n beta1 = %.6f\n beta2 = %.6f\n",
                b0, b1, b2);
        System.out.printf("Fitness (MSE) = %.6f\n\n", best.getFitness());

        // 4) Imprimir ecuación final
        System.out.printf("Ecuación final:\nSales = %.6f + %.6f * Advertising + %.6f * Index\n\n",
                b0, b1, b2);

        // 5) Simular al menos 5 experimentos: pares (x1, x2) desconocidos
        // Generamos 5 ejemplos nuevos (Advertising aleatorio en rango observado ±10, index continuo)
        double[] x1obs = ds.getX1();
        double minX1 = Double.MAX_VALUE, maxX1 = -Double.MAX_VALUE;
        for (double v : x1obs) {
            if (v < minX1) minX1 = v;
            if (v > maxX1) maxX1 = v;
        }

        Random rnd = new Random(12345);
        System.out.println("Simulaciones (5 experimentos nuevos):");
        for (int i = 0; i < 5; i++) {
            // advertising random dentro de [min-5, max+5]
            double x1 = minX1 - 5 + rnd.nextDouble() * (maxX1 - minX1 + 10);
            // index sugerimos seguir secuencia (10,11,12...) o aleatorio
            double x2 = ds.size() + 1 + i; // 10,11,12,13,14
            double pred = b0 + b1 * x1 + b2 * x2;
            System.out.printf("Experimento %d: Advertising (x1)=%.2f, Index (x2)=%.2f -> Predicted Sales (yield)=%.2f\n",
                    i + 1, x1, x2, pred);
        }

        System.out.println("\nFin del programa.");
    }
}
