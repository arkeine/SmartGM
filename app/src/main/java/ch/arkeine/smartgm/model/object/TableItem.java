package ch.arkeine.smartgm.model.object;

import ch.arkeine.smartgm.component.ProbabilityWeight;

/**
 * Created by Arkeine on 16.11.2015.
 */
public class TableItem extends ObjectWithIdentifier implements ProbabilityWeight {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableItem(long id, long tableId) {
        this(id, tableId, "", 1);
    }

    public TableItem(long id, long tableId, String title, int weight) {
        this(id, tableId, title, "", weight);
    }

    public TableItem(long id, long tableId, String title, String description, int weight) {
        super(id);
        this.tableId = tableId;
        this.title = title;
        this.description = description;
        this.weight = weight;
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return "TableItem{" +
                "title='" + title + '\'' +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long tableId;
    private String title;
    private double weight;
    private String description;
}
