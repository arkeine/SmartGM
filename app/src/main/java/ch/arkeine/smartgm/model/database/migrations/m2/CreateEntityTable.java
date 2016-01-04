package ch.arkeine.smartgm.model.database.migrations.m2;

import android.database.sqlite.SQLiteDatabase;

import ch.arkeine.smartgm.model.database.DataBaseSyntaxTool;
import ch.arkeine.smartgm.model.database.contracts.EntityTableContract;
import ch.arkeine.smartgm.model.database.contracts.GameTableContract;
import ch.arkeine.smartgm.model.database.contracts.UniverseTableContract;
import ch.arkeine.smartgm.model.database.migrations.DataBaseMigration;

import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.INT_TYPE;
import static ch.arkeine.smartgm.model.database.DataBaseSyntaxTool.TEXT_TYPE;

/**
 * This class represent a migration on the data base model.
 *
 * This migration will create a new table which represent an entity
 */
public final class CreateEntityTable implements DataBaseMigration {

    /* ============================================ */
    // TABLE CONTRACT
    /* ============================================ */

    /*
     * The migration keep a copy of the contract a the migration time.
     *
     * This allow to always migrate or roll back event if the contract change.
     */

    public static final String TABLE_NAME = "entities";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_UNIVERSE = "universe_id";
    public static final String COLUMN_GAME = "game_id";
    public static final String COLUMN_PARENT = "parent_id";

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
                COLUMN_ID, COLUMN_UNIVERSE, COLUMN_GAME, COLUMN_PARENT, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_TYPE};
    }
    public static final String[] columnTypeOrder(){
        return new String[]{
                INT_TYPE, INT_TYPE, INT_TYPE, INT_TYPE, TEXT_TYPE, TEXT_TYPE, INT_TYPE};
    }

    public static final String SQL_CREATE_ENTRIES = DataBaseSyntaxTool.createTable(
            TABLE_NAME, columnNameOrder(), columnTypeOrder(), new String[]{COLUMN_GAME, COLUMN_PARENT},
            DataBaseSyntaxTool.createPrimaryKey(COLUMN_ID),
            new String[]{
                    DataBaseSyntaxTool.createForeignConstraintKeyNullable(COLUMN_GAME,
                            GameTableContract.TABLE_NAME, GameTableContract.COLUMN_ID),
                    DataBaseSyntaxTool.createForeignConstraintKeyDelete(
                            COLUMN_UNIVERSE, UniverseTableContract.TABLE_NAME, UniverseTableContract.COLUMN_ID),
                    DataBaseSyntaxTool.createForeignConstraintKeyNullable(COLUMN_PARENT,
                            EntityTableContract.TABLE_NAME, EntityTableContract.COLUMN_ID)
            }
    );

    public static final String SQL_DELETE_ENTRIES =
            DataBaseSyntaxTool.DELETE_TABLE + TABLE_NAME;


}
