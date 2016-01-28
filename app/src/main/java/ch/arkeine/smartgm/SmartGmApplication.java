package ch.arkeine.smartgm;

import android.app.Application;

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

    public static MainFilterManager filterManager = new MainFilterManager();

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public SmartGmApplication() {
        instance = this;
    }

}
