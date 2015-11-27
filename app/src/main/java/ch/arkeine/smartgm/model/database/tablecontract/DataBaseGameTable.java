package ch.arkeine.smartgm.model.database.tablecontract;

import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.INT_TYPE;
import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.TEXT_TYPE;

/**
 * Created by Arkeine on 08.11.2015.
 */
public final class DataBaseGameTable {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "games";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static final String[] columnNameOrder(){
        return new String[]{
                COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, TEXT_TYPE, TEXT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME,columnNameOrder(),columnTypeOrder(),
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID), new String[]{}
    );

    public static final String SQL_DELETE_ENTRIES =
            DataBaseSyntaxTool.DELETE_TABLE + TABLE_NAME;

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Only static class, can't be instantiate
     */
    private DataBaseGameTable() {
    }
}
