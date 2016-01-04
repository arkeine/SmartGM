package ch.arkeine.smartgm.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * This class allow to store item (like a list) and pick a random item from the list based on
 * the item weight (the item with a big weight will be picked more often)
 * @param <T> Type of content. The items object must implement the ProbabilityWeight interface
 */
public class ProbabilityList<T extends ProbabilityList.ProbabilityWeight> extends ArrayList<T> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ProbabilityList() {
        this.random = new Random();
        this.listSumWeight = new ArrayList<>();
        this.currentWeight = 0;
        this.mustNormalize = false;
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public boolean add(T object) {
        mustNormalize = true;
        return super.add(object);
    }

    @Override
    public void add(int index, T object) {
        mustNormalize = true;
        super.add(index, object);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        mustNormalize = true;
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        mustNormalize = true;
        return super.addAll(index, collection);
    }

    @Override
    public void clear() {
        mustNormalize = true;
        super.clear();
    }

    @Override
    public T remove(int index) {
        mustNormalize = true;
        return super.remove(index);
    }

    @Override
    public boolean remove(Object object) {
        mustNormalize = true;
        return super.remove(object);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        mustNormalize = true;
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public T set(int index, T object) {
        mustNormalize = true;
        return super.set(index, object);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public T getRandomValue() {
        if (mustNormalize) {
            normalizeValues();
            mustNormalize = false;
        }

        double r = random.nextDouble() * currentWeight;

        for (int i = 0; i < this.size(); ++i) {
            if (r <= listSumWeight.get(i)) return this.get(i);
        }
        return null;
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void normalizeValues() {
        listSumWeight.clear();

        currentWeight = 0;
        for (int i = 0; i < this.size(); ++i) {
            currentWeight += this.get(i).getWeight();
            listSumWeight.add(currentWeight);
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private Random random;
    private boolean mustNormalize;
    private double currentWeight;
    private List<Double> listSumWeight;

    /* ============================================ */
    // INTERNAL CLASS / INTERFACE
    /* ============================================ */

    public interface ProbabilityWeight {
        double getWeight();
    }
}
