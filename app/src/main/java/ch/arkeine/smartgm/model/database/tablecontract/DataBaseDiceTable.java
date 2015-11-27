package ch.arkeine.smartgm.model.database.tablecontract;

import static ch.arkeine.smartgm.model.database.tablecontract.DataBaseSyntaxTool.INT_TYPE;

/**
 * Created by Arkeine on 08.11.2015.
 */
public final class DataBaseDiceTable {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "dices";
    public static final String COLUMN_ID = "pk_id";
    public static final String COLUMN_NBFACE = "nbFace";
    public static final String COLUMN_GAME = "fk_game";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static String[] columnNameOrder(){
        return new String[]{
                COLUMN_ID, COLUMN_GAME, COLUMN_NBFACE};
    }
    public static String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, INT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME,columnNameOrder(),columnTypeOrder(),
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID),
            new String[]{DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                    COLUMN_GAME, DataBaseGameTable.TABLE_NAME, DataBaseGameTable.COLUMN_ID
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
    private DataBaseDiceTable() {
    }
}
