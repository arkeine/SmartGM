package ch.arkeine.smartgm.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ch.arkeine.smartgm.model.database.tablecontract.DataBaseCharacterTable;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseDiceTable;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseGameTable;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseStatisticTable;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseTableItemTable;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseTableTable;

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
        db.execSQL(DataBaseGameTable.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseDiceTable.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseTableTable.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseTableItemTable.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseCharacterTable.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseStatisticTable.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseGameTable.SQL_DELETE_ENTRIES);
        db.execSQL(DataBaseDiceTable.SQL_DELETE_ENTRIES);
        db.execSQL(DataBaseTableTable.SQL_DELETE_ENTRIES);
        db.execSQL(DataBaseTableItemTable.SQL_DELETE_ENTRIES);
        db.execSQL(DataBaseCharacterTable.SQL_DELETE_ENTRIES);
        db.execSQL(DataBaseStatisticTable.SQL_DELETE_ENTRIES);
        onCreate(db);
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
    // CONSTANTS
    /* ============================================ */

    public static final int DATABASE_VERSION = 27;
    public static final String DATABASE_NAME = "smartgm.db";

    private static final String ENABLE_FK = "PRAGMA foreign_keys=ON;";
}
