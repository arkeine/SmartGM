package ch.arkeine.smartgm.model.database.migrations;

import ch.arkeine.smartgm.model.database.DataBaseSyntaxTool;
import ch.arkeine.smartgm.model.database.contracts.UniverseTableContract;

import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.INT_TYPE;
import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.TEXT_TYPE;

/**
 * Created by Arkeine on 08.11.2015.
 */
public final class DataBaseSettingTable {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    public static final String TABLE_NAME = "settings";
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_VALUE = "value";
    public static final String COLUMN_UNIVERSE = "universe_id";

    /* ============================================ */
    // TOOLS
    /* ============================================ */

    public static final String[] columnNameOrder(){
        return new String[]{
                COLUMN_KEY, COLUMN_UNIVERSE, COLUMN_VALUE};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, TEXT_TYPE, TEXT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME, columnNameOrder(), columnTypeOrder(), null,
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_KEY),
            new String[]{DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                    COLUMN_UNIVERSE, UniverseTableContract.TABLE_NAME, UniverseTableContract.COLUMN_ID
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
    private DataBaseSettingTable() {
    }
}
