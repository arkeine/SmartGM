package ch.arkeine.smartgm;

import android.app.Application;
import android.content.ContentValues;

/**
 * Created by Arkeine on 06.11.2015.
 */
public class SmartGmApplication extends Application {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public SmartGmApplication() {
        this.values = new ContentValues();
        this.filterManager = new MainFilterManager();
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
