package ch.arkeine.smartgm.model.database.dal;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import ch.arkeine.smartgm.model.database.DataBaseHelper;

/**
 * Parent of all object which are in the data layer (access to the data storage).
 * Provide methods to manipulate the helper (open and close operation).
 */
public abstract class DataBasePersistence {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DataBasePersistence(Context context) {
        this.context = context;
        open();
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void open() throws SQLException {
        if (dbh == null)
            dbh = DataBaseHelper.getHelper(context);
        db = dbh.getWritableDatabase();
    }

    public void close() {
        dbh.close();
        db = null;
    }

    public Context getContext() {
        return context;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    protected SQLiteDatabase db;
    private DataBaseHelper dbh;
    private Context context;
}
