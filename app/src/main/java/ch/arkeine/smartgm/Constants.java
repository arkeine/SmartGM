package ch.arkeine.smartgm;

/**
 * Created by Arkeine on 12.11.2015.
 */
public final class Constants {


    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    // Intents (communication between activity)
    public static final String MODE_TITLE = "MODE";
    public static final String MODE_CREATE = "CREATE";
    public static final String MODE_MODIFY = "MODIFY";
    public static final String MODE_USE = "USE";
    public static final String ID_TITLE = "ID";

    // Invalid ID for SQLite databases

    public static final long invalidId = -1;

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static String getOrDefault(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Only static class, can't be instantiate
     */
    private Constants() {
    }
}
