package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.Wiki;
import ch.arkeine.smartgm.model.helper.DataBaseHandler;
import ch.arkeine.smartgm.view.activity.editiondb.WikiEditionActivity;
import nucleus.presenter.Presenter;

/**
 * Presenter for the wiki edition
 */
public class WikiEditionPresenter extends Presenter<WikiEditionActivity>{

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        wiki = new Wiki();
        this.helper = SmartGmApplication.createDataBaseHandler();
        //No restore, no need backup on process fail
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.helper.close();
    }


    @Override
    protected void onTakeView(WikiEditionActivity activity) {
        super.onTakeView(activity);
        publish();
    }

    @Override
    protected void onDropView() {
        super.onDropView();

        wiki.setName(getView().getName());
        wiki.setDescription(getView().getDescription());
        fkUniverse = getView().getUniverse();

        if(fkUniverse != null && getView().isSaveToDatabase()){
            helper.getSession().getWikiDao().insertOrReplace(wiki);
            wiki.setUniverse(fkUniverse);
            helper.getSession().getWikiDao().update(wiki);
        }
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void reloadData(){
        publish();
    }

    public void externalDescriptionUpdate(String description){
        wiki.setDescription(description);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void publish() {
        List<Universe> listUniverse = helper.getSession().getUniverseDao().loadAll();
        getView().setUniverseList(listUniverse);

        loadWiki(getView().getId());
        getView().setName(wiki.getName());
        getView().setDescription(wiki.getDescription());
        getView().setUniverse(fkUniverse);
    }

    private void loadWiki(long id) {
        if(id == Constants.INVALID_ID) {
            if (wiki.getId() != null){
                wiki = new Wiki();
                fkUniverse = null;
            }
        } else {
            if (wiki.getId() == null || id != wiki.getId()) {
                wiki = helper.getSession().getWikiDao().load(id);
                fkUniverse = wiki.getUniverse();
            }
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataBaseHandler helper;
    private Wiki wiki;
    private Universe fkUniverse;

}
