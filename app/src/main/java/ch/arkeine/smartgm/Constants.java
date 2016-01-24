package ch.arkeine.smartgm;

/**
 * This class hold constants which are shared by many classes
 */
public final class Constants {


    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    //Default duration before switch automatically to the next view
    public static final int WELCOME_DEFAULT_TIMEOUT = 500;

    // Keyword for communicate the content in intent
    public static final String KEY_MODE_CONTENT = "MODE";
    public static final String KEY_ID_CONTENT = "ID";
    public static final String KEY_ENTITY_TYPE_CONTENT = "ENTITY_TYPE";
    public static final String KEY_DESCRIPTION_CONTENT = "DESCRIPTION";

    // Mode type of an edition activity
    public static final String MODE_CREATE = "CREATE";
    public static final String MODE_MODIFY = "MODIFY";
    public static final String MODE_USE = "USE";

    // Invalid ID for SQLite databases
    public static final long INVALID_ID = -1;

    // Format pattern for the date
    public static final String DATE_PATTERN = "dd.MM.yyyy";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static String getOrDefault(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }
}
