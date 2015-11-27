package ch.arkeine.smartgm.model.object;

import ch.arkeine.smartgm.component.ProbabilityList;

/**
 * Created by arkeine on 11/9/15.
 */
public class Table extends ObjectWithGameIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Table(long id, long gameId) {
        this(id, gameId, "");
    }

    public Table(long id, long gameId, String name) {
        super(id, gameId);
        this.name = name;
        this.listTableItem = new ProbabilityList<>();
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", listTableItem=" + listTableItem +
                '}';
    }
/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public ProbabilityList<TableItem> getItemList() {
        return listTableItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* ============================================ */
    // DELEGATE
    /* ============================================ */

    public boolean add(TableItem object) {
        return listTableItem.add(object);
    }

    public boolean remove(Object object) {
        return listTableItem.remove(object);
    }

    public TableItem getRandomValue() {
        return listTableItem.getRandomValue();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private String name;
    private ProbabilityList<TableItem> listTableItem;
}
