package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.handler.DataBaseHandler;
import ch.arkeine.smartgm.view.activity.edition.EditionListActivity;
import nucleus.presenter.Presenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

/**
 * Presenter for the all data type listing
 */
public class EditionListPresenter extends Presenter<EditionListActivity> {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        this.universe = new Universe();
        this.universe.setId(savedState.getLong(ATR_ID, Constants.INVALID_ID));
        this.universe.setName(getOrDefault(savedState.getString(ATR_NAME), ""));
        this.universe.setDescription(getOrDefault(savedState.getString(ATR_DESCRIPTION), ""));
    }

    @Override
    protected void onSave(@NonNull Bundle savedState) {
        super.onSave(savedState);
        savedState.putLong(ATR_ID, universe.getId());
        savedState.putString(ATR_NAME, universe.getName());
        savedState.putString(ATR_DESCRIPTION, universe.getDescription());
    }

    @Override
    protected void onTakeView(EditionListActivity editionListActivity) {
        super.onTakeView(editionListActivity);
        activity = editionListActivity;
        publish();
    }

    @Override
    protected void onDropView() {
        super.onDropView();
        activity = null;
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
        if (activity != null){
            //universe.setName(activity.getName());
            //universe.setDescription(activity.getDescription());

            DataBaseHandler helper = SmartGmApplication.createDataBaseHandler();
            helper.getSession().getUniverseDao().insertOrReplace(universe);
            helper.close();
        }
    }

    public void reloadData(){
        publish();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void publish(){
        if(activity != null){
            //activity.setDescription(universe.getDescription());
            //activity.setName(universe.getName());
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private Universe universe;
    private EditionListActivity activity;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static String ATR_ID = EditionListPresenter.class.getName()+"id";
    private static String ATR_NAME = EditionListPresenter.class.getName()+"name";
    private static String ATR_DESCRIPTION = EditionListPresenter.class.getName()+"description";
}
