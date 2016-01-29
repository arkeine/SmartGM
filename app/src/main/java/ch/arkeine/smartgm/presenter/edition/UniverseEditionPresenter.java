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
        createNew();
        this.helper = SmartGmApplication.createDataBaseHandler();
        //No restore, no need backup on process fail
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getView().isSaveToDatabase()){
            helper.getSession().getUniverseDao().insertOrReplace(universe);
        }
        this.helper.close();
    }


    @Override
    protected void onTakeView(UniverseEditionActivity universeEditionActivity) {
        super.onTakeView(universeEditionActivity);
        publish();
    }

    @Override
    protected void onDropView() {
        super.onDropView();

        universe.setName(getView().getName());
        universe.setDescription(getView().getDescription());
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void reloadData(){
        publish();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void publish() {
        loadUniverse(getView().getId());
        getView().setName(universe.getName());
        getView().setDescription(universe.getDescription());
    }

    private void loadUniverse(long universeId) {
        if(universeId == Constants.INVALID_ID)
            if(universe.getId() != null)
                createNew();
        else
            if(universe.getId() == null || universeId != universe.getId())
                universe = helper.getSession().getUniverseDao().load(universeId);
    }

    private void createNew(){
        universe = new Universe();
        universe.setDescription("");
        universe.setName("");
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataBaseHandler helper;
    private Universe universe;
}
