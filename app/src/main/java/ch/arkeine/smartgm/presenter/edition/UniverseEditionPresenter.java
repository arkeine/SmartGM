package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.handler.DataBaseHandler;
import ch.arkeine.smartgm.view.activity.edition.UniverseEditionActivity;
import nucleus.presenter.Presenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

/**
 * Presenter for the universe edition
 */
public class UniverseEditionPresenter extends Presenter<UniverseEditionActivity> {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        this.universe = new Universe();
        if(savedState != null) {
            this.universe.setId(savedState.getLong(ATR_ID, Constants.INVALID_ID));
            this.universe.setName(getOrDefault(savedState.getString(ATR_NAME), ""));
            this.universe.setDescription(getOrDefault(savedState.getString(ATR_DESCRIPTION), ""));
        }
        Log.d("PRESENTER TEST", "PRESENTER onCreate");
    }

    @Override
    protected void onSave(@NonNull Bundle savedState) {
        super.onSave(savedState);
        if(universe.getId() != null) {
            savedState.putLong(ATR_ID, universe.getId());
        }
            savedState.putString(ATR_NAME, universe.getName());
            savedState.putString(ATR_DESCRIPTION, universe.getDescription());

        Log.d("PRESENTER TEST", "PRESENTER onSave");
    }

    @Override
    protected void onTakeView(UniverseEditionActivity universeEditionActivity) {
        super.onTakeView(universeEditionActivity);
        publish();

        Log.d("PRESENTER TEST", "PRESENTER onTakeView");
    }

    @Override
    protected void onDropView() {
        super.onDropView();

        Log.d("PRESENTER TEST", "PRESENTER onDropView");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("PRESENTER TEST", "PRESENTER onDestroy");
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    /**
     * Give a universe ID or Constants.INVALID_ID for new universe
     */
    public void loadUniverse(long universeId) {
        if(universeId == Constants.INVALID_ID){
            universe = new Universe();
        }else if(universeId != universe.getId()){
            DataBaseHandler helper = SmartGmApplication.createDataBaseHandler();
            universe = helper.getSession().getUniverseDao().load(universeId);
            helper.close();
        }
    }

    public void saveUniverse(){
        universe.setName(getView().getName());
        universe.setDescription(getView().getDescription());

        DataBaseHandler helper = SmartGmApplication.createDataBaseHandler();
        helper.getSession().getUniverseDao().insertOrReplace(universe);
        helper.close();
    }

    public void reloadData(){
        publish();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void publish(){
        getView().setDescription(universe.getDescription());
        getView().setName(universe.getName());
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private Universe universe;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static String ATR_ID = UniverseEditionPresenter.class.getName()+"id";
    private static String ATR_NAME = UniverseEditionPresenter.class.getName()+"name";
    private static String ATR_DESCRIPTION = UniverseEditionPresenter.class.getName()+"description";
}
