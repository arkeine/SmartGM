package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.helper.DataBaseHandler;
import ch.arkeine.smartgm.view.activity.editiondb.UniverseEditionActivity;
import nucleus.presenter.Presenter;

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
        universe = new Universe();
        this.helper = SmartGmApplication.createDataBaseHandler();
        //No restore, no need backup on process fail
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.helper.close();
    }


    @Override
    protected void onTakeView(UniverseEditionActivity activity) {
        super.onTakeView(activity);
        publish();
    }

    @Override
    protected void onDropView() {
        super.onDropView();

        universe.setName(getView().getName());
        universe.setDescription(getView().getDescription());

        if(getView().isSaveToDatabase()){
            helper.getSession().getUniverseDao().insertOrReplace(universe);
        }
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

    private void loadUniverse(long id) {
        if(id == Constants.INVALID_ID) {
            if (universe.getId() != null)
                universe = new Universe();
        }
        else {
            if (universe.getId() == null || id != universe.getId())
                universe = helper.getSession().getUniverseDao().load(id);
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataBaseHandler helper;
    private Universe universe;
}
