package ch.arkeine.smartgm.model.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Provide some methods to help in creating data base
 */
public final class DataBaseSyntaxTool {

    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    // Vocabulary
    public static final String TEXT_TYPE = " TEXT ";
    public static final String INT_TYPE = " INTEGER ";
    public static final String NOT_NULL = " NOT NULL ";
    public static final String PRIMARY = " PRIMARY KEY ";
    public static final String FOREIGN = " FOREIGN KEY ";
    public static final String REFERENCE = " REFERENCES ";
    public static final String CREATE_TABLE = " CREATE TABLE ";
    public static final String DELETE_TABLE = " DROP TABLE IF EXISTS ";
    public static final String DELETE_CASCADE = " ON DELETE CASCADE ";
    public static final String DELETE_NULL = " ON DELETE SET NULL ";
    public static final String COMMA_SEP = ",";
    public static final String COTE_SEP = "\"";
    public static final String BRACKET_OPEN = "(";
    public static final String BRACKET_CLOSE = ")";

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
                                     String[] columnNullable,
                                     String primaryKeyStatement, String[] foreignKeyStatement) {
        if (columnName.length != columnType.length) return "";

        List<String> listNullable = columnNullable == null ? new ArrayList():Arrays.asList(columnNullable);

        StringBuilder sb = new StringBuilder();
        sb.append(CREATE_TABLE);
        sb.append(tableName);
        sb.append(BRACKET_OPEN);
        for (int i = 0; i < columnName.length; ++i) {
            sb.append(columnName[i]);
            sb.append(" ");
            sb.append(columnType[i]);
            if(!listNullable.contains(columnName[i])) {
                sb.append(NOT_NULL);
            }
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
        return createForeignKey(childColumnName, parentTableName, parentColumnName) + DELETE_CASCADE;
    }

    public static String createForeignConstraintKeyNullable(String childColumnName,
                                                             String parentTableName,
                                                             String parentColumnName) {
        return createForeignKey(childColumnName, parentTableName, parentColumnName) + DELETE_NULL;
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
