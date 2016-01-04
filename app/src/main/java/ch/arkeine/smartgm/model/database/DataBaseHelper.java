package ch.arkeine.smartgm.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ch.arkeine.smartgm.model.database.migrations.m1.CreateDiceTable;
import ch.arkeine.smartgm.model.database.migrations.m1.CreateGameTable;
import ch.arkeine.smartgm.model.database.migrations.m1.CreateTableItemTable;
import ch.arkeine.smartgm.model.database.migrations.m1.CreateTableTable;
import ch.arkeine.smartgm.model.database.migrations.m1.CreateUniverseTable;
import ch.arkeine.smartgm.model.database.migrations.m1.CreateWikiTable;
import ch.arkeine.smartgm.model.database.migrations.m2.CreateEntityTable;
import ch.arkeine.smartgm.model.database.migrations.m2.CreateEntityValueTable;
import ch.arkeine.smartgm.model.database.migrations.m2.CreateTimeLineTable;

/**
 * Created by Arkeine on 08.11.2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    /* ============================================ */
    // CONSTRUCTOR SINGLETON
    /* ============================================ */

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        while(oldVersion < newVersion)
        {
            doMigrationNumber(oldVersion, db);
            ++oldVersion;
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL(ENABLE_FK);
        }
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void doMigrationNumber(int version, SQLiteDatabase db)
    {
        switch (version)
        {
            case 0:
                new CreateUniverseTable().migrate(db);
                new CreateDiceTable().migrate(db);
                new CreateGameTable().migrate(db);
                new CreateTableTable().migrate(db);
                new CreateTableItemTable().migrate(db);
                new CreateWikiTable().migrate(db);
                break;
            case 1:
                new CreateEntityTable().migrate(db);
                new CreateEntityValueTable().migrate(db);
                new CreateTimeLineTable().migrate(db);
                break;
        }
    }

    /* ============================================ */
    // CONSTANTS
    /* ============================================ */

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "smartgm.db";

    private static final String ENABLE_FK = "PRAGMA foreign_keys=ON;";
}
