package ch.arkeine.smartgm.model.database.contracts;

/**
 * Current columns name in the database.
 * The value should be always the same as the database.
 */
public final class EntityValueTableContract {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "entities_values";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_ENTITY = "entity_id";
}
