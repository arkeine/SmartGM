package ch.arkeine.smartgm.model.dao.object;

import ch.arkeine.smartgm.component.ProbabilityList;

/**
 * DAO pattern. Represent a table store in the database in the object world.
 */
public class Table extends ObjectDescribable {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Table(long id, long universeId) {
        super(id);
        this.universeId = universeId;
        this.listTableItem = new ProbabilityList<>();
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getUniverseId() {
        return universeId;
    }

    public void setUniverseId(long universeId) {
        this.universeId = universeId;
    }

    public ProbabilityList<TableItem> getItemList() {
        return listTableItem;
    }

    /* ============================================ */
    // DELEGATE
    /* ============================================ */

    public TableItem getRandomValue() {
        return listTableItem.getRandomValue();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long universeId;
    private ProbabilityList<TableItem> listTableItem;

}
