package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Dice;
import ch.arkeine.smartgm.model.Game;
import ch.arkeine.smartgm.model.Table;
import ch.arkeine.smartgm.model.Timeline;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.Wiki;
import ch.arkeine.smartgm.model.handler.DataBaseHandler;
import ch.arkeine.smartgm.model.handler.IdentifiedDataObject;
import ch.arkeine.smartgm.view.activity.editiondb.EditionListActivity;
import nucleus.presenter.Presenter;

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
        this.helper = SmartGmApplication.createDataBaseHandler();
        this.dataType = DATA_TYPE.UNIVERSE;
        //No restore, no need backup on process fail
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.helper.close();
    }


    @Override
    protected void onTakeView(EditionListActivity editionListActivity) {
        super.onTakeView(editionListActivity);
        publish();
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public DATA_TYPE getDataType() {
        return dataType;
    }

    public void setDataType(DATA_TYPE dataType) {
        this.dataType = dataType;
        publish();
    }

    public void remove(IdentifiedDataObject itemAtPosition){
        switch (dataType) {
            case UNIVERSE:
                helper.getSession().getUniverseDao().delete((Universe)itemAtPosition);
                break;
            case GAME:
                helper.getSession().getGameDao().delete((Game)itemAtPosition);
                break;
            case DICE:
                helper.getSession().getDiceDao().delete((Dice) itemAtPosition);
                break;
            case TABLE:
                helper.getSession().getTableDao().delete((Table)itemAtPosition);
                break;
            case WIKI:
                helper.getSession().getWikiDao().delete((Wiki)itemAtPosition);
                break;
            case TIME_LINE:
                helper.getSession().getTimelineDao().delete((Timeline)itemAtPosition);
                break;
        }
        helper.getSession().clear();
        publish();
    }

    public void externalModificationOnDataSignal(){
        helper.getSession().clear();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void publish() {
        List l;
        switch (dataType) {
            case UNIVERSE:
                l = helper.getSession().getUniverseDao().loadAll();
                break;
            case GAME:
                l = helper.getSession().getGameDao().loadAll();
                break;
            case DICE:
                l = helper.getSession().getDiceDao().loadAll();
                break;
            case TABLE:
                l = helper.getSession().getTableDao().loadAll();
                break;
            case WIKI:
                l = helper.getSession().getWikiDao().loadAll();
                break;
            case TIME_LINE:
                l = helper.getSession().getTimelineDao().loadAll();
                break;
            default:
                l = new ArrayList(0);
        }
        getView().setListContent(l);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataBaseHandler helper;
    private DATA_TYPE dataType;

    public enum DATA_TYPE {UNIVERSE, GAME, DICE, TABLE, WIKI, TIME_LINE}
}
