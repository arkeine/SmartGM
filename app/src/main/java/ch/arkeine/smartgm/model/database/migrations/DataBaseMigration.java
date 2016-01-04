package ch.arkeine.smartgm.model.database.migrations;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Arkeine on 19.12.2015.
 */
public interface DataBaseMigration {
    void migrate(SQLiteDatabase db);
    void reset(SQLiteDatabase db);
}
