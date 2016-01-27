package ch.arkeine.smartgm;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ch.arkeine.smartgm.model.DaoMaster;
import ch.arkeine.smartgm.model.DaoSession;
import ch.arkeine.smartgm.model.handler.DataBaseHandler;

/**
 * Custom application class which hold some global object
 */
public class SmartGmApplication extends Application {

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static SmartGmApplication instance;

    /**
     *
     * Use to get a database session with the context of application.
     *
     * See this post :
     * http://stackoverflow.com/questions/987072/using-application-context-everywhere
     */
    public static DataBaseHandler createDataBaseHandler()
    {
        return new DataBaseHandler(instance);
    }

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public SmartGmApplication() {
        this.values = new ContentValues();
        this.filterManager = new MainFilterManager();
        instance = this;
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public ContentValues values() {
        return values;
    }

    public MainFilterManager getFilterManager() {
        return filterManager;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private ContentValues values;
    private MainFilterManager filterManager;
}
