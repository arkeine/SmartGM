package ch.arkeine.smartgm.model.database.contracts;

/**
 * Current columns name in the database.
 * The value should be always the same as the database.
 */
public final class DiceTableContract {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "dices";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NBFACE = "faces";
    public static final String COLUMN_UNIVERSE = "universe_id";
}
