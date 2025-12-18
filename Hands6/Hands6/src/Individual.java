import java.util.Random;

public class Individual implements Comparable<Individual> {
    private double[] genes; // [beta0, beta1, beta2]
    private double fitness; // lower is better (MSE)
    private static final Random rnd = new Random();

    public Individual() {
        genes = new double[3];
        fitness = Double.POSITIVE_INFINITY;
    }

    // Initialize random genes within given ranges
    public void randomize(double[] minRange, double[] maxRange) {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = minRange[i] + rnd.nextDouble() * (maxRange[i] - minRange[i]);
        }
    }

    public double getGene(int idx) {
        return genes[idx];
    }

    public void setGene(int idx, double value) {
        genes[idx] = value;
    }

    public double[] getGenes() {
        return genes;
    }

    public void setGenes(double[] newGenes) {
        System.arraycopy(newGenes, 0, genes, 0, genes.length);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    // Copy
    public Individual copy() {
        Individual out = new Individual();
        out.setGenes(this.genes.clone());
        out.setFitness(this.fitness);
        return out;
    }

    @Override
    public int compareTo(Individual o) {
        return Double.compare(this.fitness, o.fitness);
    }
}
