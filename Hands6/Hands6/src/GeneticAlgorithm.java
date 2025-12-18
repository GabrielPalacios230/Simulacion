import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

    private DataSet data;
    private int populationSize;
    private int generations;
    private double crossoverRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean elitism;
    private Individual[] population;
    private Random rnd = new Random();

    // Ranges for genes: [beta0, beta1, beta2]
    // Ajusta si quieres otros rangos
    private double[] minRange = new double[] { -500.0, -50.0, -200.0 };
    private double[] maxRange = new double[] { 2000.0, 50.0, 200.0 };

    public GeneticAlgorithm(DataSet data,
                            int populationSize,
                            int generations,
                            double crossoverRate,
                            double mutationRate,
                            int tournamentSize,
                            boolean elitism) {
        this.data = data;
        this.populationSize = populationSize;
        this.generations = generations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
        this.elitism = elitism;

        initPopulation();
    }

    private void initPopulation() {
        population = new Individual[populationSize];
        for (int i = 0; i < populationSize; i++) {
            Individual ind = new Individual();
            ind.randomize(minRange, maxRange);
            population[i] = ind;
        }
        evaluatePopulation();
    }

    // Fitness: Mean Squared Error on dataset
    private double fitnessOf(Individual ind) {
        double[] b = ind.getGenes(); // b0, b1, b2
        double mse = 0.0;
        double[] x1 = data.getX1();
        double[] x2 = data.getX2();
        double[] y  = data.getY();
        int n = data.size();

        for (int i = 0; i < n; i++) {
            double pred = b[0] + b[1] * x1[i] + b[2] * x2[i];
            double err = y[i] - pred;
            mse += err * err;
        }

        mse = mse / n;
        return mse;
    }

    private void evaluatePopulation() {
        for (Individual ind : population) {
            ind.setFitness(fitnessOf(ind));
        }
        Arrays.sort(population); // sort by fitness ascending (best first)
    }

    private Individual tournamentSelection() {
        Individual best = null;
        for (int i = 0; i < tournamentSize; i++) {
            Individual candidate = population[rnd.nextInt(populationSize)];
            if (best == null || candidate.getFitness() < best.getFitness()) {
                best = candidate;
            }
        }
        return best.copy();
    }

    // Arithmetic crossover (blend)
    private Individual[] crossover(Individual p1, Individual p2) {
        Individual c1 = p1.copy();
        Individual c2 = p2.copy();

        if (rnd.nextDouble() < crossoverRate) {
            double alpha = rnd.nextDouble();
            double[] g1 = p1.getGenes();
            double[] g2 = p2.getGenes();
            double[] ng1 = new double[g1.length];
            double[] ng2 = new double[g1.length];
            for (int i = 0; i < g1.length; i++) {
                ng1[i] = alpha * g1[i] + (1 - alpha) * g2[i];
                ng2[i] = alpha * g2[i] + (1 - alpha) * g1[i];
            }
            c1.setGenes(ng1);
            c2.setGenes(ng2);
        }

        return new Individual[] { c1, c2 };
    }

    // Gaussian mutation per gene
    private void mutate(Individual ind) {
        double[] genes = ind.getGenes();
        for (int i = 0; i < genes.length; i++) {
            if (rnd.nextDouble() < mutationRate) {
                double range = (maxRange[i] - minRange[i]);
                double sigma = range * 0.05; // 5% of range
                genes[i] += rnd.nextGaussian() * sigma;
                // clamp to range
                if (genes[i] < minRange[i]) genes[i] = minRange[i];
                if (genes[i] > maxRange[i]) genes[i] = maxRange[i];
            }
        }
        ind.setGenes(genes);
    }

    public Individual run() {
        Individual bestSoFar = population[0].copy();

        for (int gen = 0; gen < generations; gen++) {
            Individual[] newPop = new Individual[populationSize];
            int idx = 0;

            // Elitism: keep best
            if (elitism) {
                newPop[idx++] = population[0].copy();
            }

            // Fill new population
            while (idx < populationSize) {
                Individual parent1 = tournamentSelection();
                Individual parent2 = tournamentSelection();

                Individual[] children = crossover(parent1, parent2);

                mutate(children[0]);
                if (idx < populationSize) {
                    newPop[idx++] = children[0];
                }

                if (idx < populationSize) {
                    mutate(children[1]);
                    newPop[idx++] = children[1];
                }
            }

            population = newPop;
            evaluatePopulation();

            // track best
            if (population[0].getFitness() < bestSoFar.getFitness()) {
                bestSoFar = population[0].copy();
            }

            // (Opcional) imprimir progreso cada ciertos gen
            if ((gen + 1) % Math.max(1, generations / 10) == 0) {
                System.out.printf("Gen %4d / %d  Best MSE=%.4f\n", gen + 1, generations, bestSoFar.getFitness());
            }
        }

        // devolver el mejor encontrado
        return bestSoFar;
    }
}
