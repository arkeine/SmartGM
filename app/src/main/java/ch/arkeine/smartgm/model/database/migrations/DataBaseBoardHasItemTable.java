package ch.arkeine.smartgm.model.database.migrations;

import ch.arkeine.smartgm.model.database.DataBaseSyntaxTool;
import ch.arkeine.smartgm.model.database.contracts.EntityTableContract;

import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.INT_TYPE;

/**
 * This class is a data base table description. It provide the methods for create and delete this
 * table
 */
public final class DataBaseBoardHasItemTable {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "boards_have_items";
    public static final String COLUMN_BOARD_ID = "board_id";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_POS_X = "pos_x";
    public static final String COLUMN_POS_Y = "pos_y";
    public static final String COLUMN_DIRECTION = "direction";
    public static final String COLUMN_SIZE = "size";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static final String[] columnNameOrder(){
        return new String[]{
                COLUMN_BOARD_ID, COLUMN_ITEM_ID, COLUMN_POS_X, COLUMN_POS_Y, COLUMN_DIRECTION,
                COLUMN_SIZE};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, INT_TYPE, INT_TYPE, INT_TYPE, INT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME, columnNameOrder(), columnTypeOrder(),  null,  "",
            new String[]{
                    DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                            COLUMN_BOARD_ID, DataBaseBoardTable.TABLE_NAME, DataBaseBoardTable.COLUMN_ID),
                    DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                            COLUMN_ITEM_ID, EntityTableContract.TABLE_NAME, EntityTableContract.COLUMN_ID)
            }
    );

    public static final String SQL_DELETE_ENTRIES =
            DataBaseSyntaxTool.DELETE_TABLE + TABLE_NAME;

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Only static class, can't be instantiate
     */
    private DataBaseBoardHasItemTable() {
    }
}
