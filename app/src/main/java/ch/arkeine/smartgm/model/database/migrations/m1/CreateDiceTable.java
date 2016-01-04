package ch.arkeine.smartgm.model.database.migrations.m1;

import android.database.sqlite.SQLiteDatabase;

import ch.arkeine.smartgm.model.database.DataBaseSyntaxTool;
import ch.arkeine.smartgm.model.database.contracts.UniverseTableContract;
import ch.arkeine.smartgm.model.database.migrations.DataBaseMigration;

/**
 * This class represent a migration on the data base model.
 *
 * This migration will create a new table which represent a dice
 */
public final class CreateDiceTable implements DataBaseMigration {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    /*
     * The migration keep a copy of the contract a the migration time.
     *
     * This allow to always migrate or roll back event if the contract change.
     */

    public static final String TABLE_NAME = "dices";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NBFACE = "faces";
    public static final String COLUMN_UNIVERSE = "universe_id";

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public void migrate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void reset(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private static String[] columnNameOrder(){
        return new String[]{
                COLUMN_ID,
                COLUMN_UNIVERSE,
                COLUMN_NBFACE};
    }
    private static String[] columnTypeOrder(){
        return new String[]{
                DataBaseSyntaxTool.INT_TYPE, DataBaseSyntaxTool.INT_TYPE, DataBaseSyntaxTool.INT_TYPE};
    }

    private static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME,columnNameOrder(),columnTypeOrder(), null,
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID),
            new String[]{DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                    COLUMN_UNIVERSE, UniverseTableContract.TABLE_NAME, UniverseTableContract.COLUMN_ID
            )}
    );

    private static final String SQL_DELETE_ENTRIES =
            DataBaseSyntaxTool.DELETE_TABLE + TABLE_NAME;
}
