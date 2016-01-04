package ch.arkeine.smartgm.model.database.migrations.m1;

import android.database.sqlite.SQLiteDatabase;

import ch.arkeine.smartgm.model.database.DataBaseSyntaxTool;
import ch.arkeine.smartgm.model.database.contracts.TableTableContract;
import ch.arkeine.smartgm.model.database.migrations.DataBaseMigration;

import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.INT_TYPE;
import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.TEXT_TYPE;

/**
 * This class represent a migration on the data base model.
 *
 * This migration will create a new table which represent a table item
 */
public final class CreateTableItemTable implements DataBaseMigration {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    /*
     * The migration keep a copy of the contract a the migration time.
     *
     * This allow to always migrate or roll back event if the contract change.
     */

    public static final String TABLE_NAME = "tables_items";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_TABLE = "table_id";

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

    public static final String[] columnNameOrder(){
        return new String[]{
                COLUMN_ID, COLUMN_TABLE, COLUMN_NAME, COLUMN_WEIGHT};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, TEXT_TYPE, INT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME, columnNameOrder(), columnTypeOrder(), null,
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID),
            new String[]{DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                    COLUMN_TABLE, CreateTableTable.TABLE_NAME, TableTableContract.COLUMN_ID
            )}
    );

    public static final String SQL_DELETE_ENTRIES =
            DataBaseSyntaxTool.DELETE_TABLE + TABLE_NAME;
}
