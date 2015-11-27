package ch.arkeine.smartgm.model.database.tablecontract;

/**
 * Created by Arkeine on 08.11.2015.
 */
public final class DataBaseSyntaxTool {

    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    // Vocabulary
    protected static final String TEXT_TYPE = " TEXT ";
    protected static final String INT_TYPE = " INTEGER ";
    protected static final String NOT_NULL = " NOT NULL ";
    protected static final String PRIMARY = " PRIMARY KEY ";
    protected static final String FOREIGN = " FOREIGN KEY ";
    protected static final String REFERENCE = " REFERENCES ";
    protected static final String CREATE_TABLE = " CREATE TABLE ";
    protected static final String DELETE_TABLE = " DROP TABLE IF EXISTS ";
    protected static final String DELETE_CASCADE = " ON DELETE CASCADE ";
    protected static final String COMMA_SEP = ",";
    protected static final String COTE_SEP = "\"";
    protected static final String BRACKET_OPEN = "(";
    protected static final String BRACKET_CLOSE = ")";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    /**
     * Create a table in sqlite format
     *
     * @param tableName
     * @param columnName
     * @param columnType
     * @param primaryKeyStatement a statement generated with the method createPrimaryKey
     * @param foreignKeyStatement a statement generated with the method createForeignKey or createForeignConstraintKeyDelete
     * @return
     */
    public static String createTable(String tableName, String[] columnName, String[] columnType,
                                     String primaryKeyStatement, String[] foreignKeyStatement) {
        if (columnName.length != columnType.length) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(CREATE_TABLE);
        sb.append(tableName);
        sb.append(BRACKET_OPEN);
        for (int i = 0; i < columnName.length; ++i) {
            sb.append(columnName[i]);
            sb.append(" ");
            sb.append(columnType[i]);
            sb.append(NOT_NULL);
            sb.append(COMMA_SEP);
        }
        sb.delete(sb.lastIndexOf(COMMA_SEP), sb.length());
        sb.append(primaryKeyStatement);
        for (String s : foreignKeyStatement) {
            sb.append(s);
        }
        sb.append(BRACKET_CLOSE);

        return sb.toString();
    }

    public static String createForeignKey(String childColumnName,
                                          String parentTableName,
                                          String parentColumnName) {
        return COMMA_SEP + FOREIGN + BRACKET_OPEN + childColumnName + BRACKET_CLOSE + REFERENCE +
                parentTableName + BRACKET_OPEN + parentColumnName + BRACKET_CLOSE;
    }

    public static String createForeignConstraintKeyDelete(String childColumnName,
                                                          String parentTableName,
                                                          String parentColumnName) {
        return COMMA_SEP + FOREIGN + BRACKET_OPEN + childColumnName + BRACKET_CLOSE + REFERENCE +
                parentTableName + BRACKET_OPEN + parentColumnName + BRACKET_CLOSE + DELETE_CASCADE;
    }

    public static String createPrimaryKey(String columnName) {
        return COMMA_SEP + PRIMARY + BRACKET_OPEN + columnName + BRACKET_CLOSE;
    }

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Only static class, can't be instantiate
     */
    private DataBaseSyntaxTool() {
    }
}
