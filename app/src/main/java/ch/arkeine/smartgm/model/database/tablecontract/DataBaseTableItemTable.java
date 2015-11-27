package ch.arkeine.smartgm.model.database.tablecontract;

import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.INT_TYPE;
import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.TEXT_TYPE;

/**
 * Created by Arkeine on 08.11.2015.
 */
public final class DataBaseTableItemTable {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "table_items";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_TABLE = "fk_table";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static final String[] columnNameOrder(){
        return new String[]{
                COLUMN_ID, COLUMN_TABLE, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_WEIGHT};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, TEXT_TYPE, TEXT_TYPE, INT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME,columnNameOrder(), columnTypeOrder(),
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID),
            new String[]{DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                    COLUMN_TABLE, DataBaseTableTable.TABLE_NAME, DataBaseTableTable.COLUMN_ID
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
    private DataBaseTableItemTable() {
    }
}
