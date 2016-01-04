package ch.arkeine.smartgm.model.database.contracts;

/**
 * Current columns name in the database.
 * The value should be always the same as the database.
 */
public final class EntityTableContract {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "entities";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_UNIVERSE = "universe_id";
    public static final String COLUMN_GAME = "game_id";
    public static final String COLUMN_PARENT = "parent_id";
}
