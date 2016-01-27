package ch.arkeine.smartgm.model.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.DaoMaster;
import ch.arkeine.smartgm.model.DaoSession;

/**
 * Created by Arkeine on 27.01.2016.
 */
public class DataBaseHandler {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DataBaseHandler(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(
                context, Constants.DB_NAME, null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        session = daoMaster.newSession();
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public DaoSession getSession()
    {
        return session;
    }

    public void close()
    {
        db.close();
        db = null;
        session = null;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private SQLiteDatabase db;
    private DaoSession session;
}
