package ch.arkeine.smartgm.model.database.contracts;

/**
 * Current columns name in the database.
 * The value should be always the same as the database.
 */
public final class GameTableContract {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "games";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PLAYERS = "players";
    public static final String COLUMN_UNIVERSE = "universe_id";
}
