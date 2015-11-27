package ch.arkeine.smartgm.model.database.tablecontract;

import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.INT_TYPE;
import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.TEXT_TYPE;

/**
 * Created by Arkeine on 08.11.2015.
 */
public final class DataBaseStatisticTable {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "statistics";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MIN = "min";
    public static final String COLUMN_CURRENT = "current";
    public static final String COLUMN_MAX = "max";
    public static final String COLUMN_CHARACTER = "fk_character";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static final String[] columnNameOrder(){
        return new String[]{
                COLUMN_ID, COLUMN_CHARACTER, COLUMN_NAME, COLUMN_MIN,
                COLUMN_CURRENT, COLUMN_MAX};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, TEXT_TYPE, INT_TYPE, INT_TYPE, INT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME,
            new String[]{COLUMN_ID, COLUMN_CHARACTER, COLUMN_CURRENT, COLUMN_MAX,
                    COLUMN_MIN, COLUMN_NAME},columnTypeOrder(),
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID),
            new String[]{DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                    COLUMN_CHARACTER, DataBaseCharacterTable.TABLE_NAME, DataBaseCharacterTable.COLUMN_ID
            )}
    );

    public static final String SQL_DELETE_ENTRIES =
            DataBaseSyntaxTool.DELETE_TABLE + TABLE_NAME;

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Only static class, can't be instantiate
     */
    private DataBaseStatisticTable() {
    }
}
