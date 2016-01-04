package ch.arkeine.smartgm.model.dao.object;

/**
 * DAO pattern. Represent a value store in the database in the object world.
 */
public class EntityValue extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public EntityValue(long id, long itemId) {
        super(id);
        this.itemId = itemId;
        this.name = "";
        this.value = 0;
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getEntityId() {
        return itemId;
    }

    public void setEntityId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long itemId;
    private String name;
    private int value;
}
