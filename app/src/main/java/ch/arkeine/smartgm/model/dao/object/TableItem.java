package ch.arkeine.smartgm.model.dao.object;

import ch.arkeine.smartgm.component.ProbabilityList;

/**
 * DAO pattern. Represent a table item store in the database in the object world.
 */
public class TableItem extends ObjectWithIdentifier implements ProbabilityList.ProbabilityWeight {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableItem(long id, long tableId) {
        super(id);
        this.tableId = tableId;
        this.name = "";
        this.weight = 1;
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return name;
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long tableId;
    private String name;
    private double weight;
}
