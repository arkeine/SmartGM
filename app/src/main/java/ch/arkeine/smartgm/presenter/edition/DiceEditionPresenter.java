package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Dice;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.helper.DataBaseHandler;
import ch.arkeine.smartgm.view.activity.editiondb.DiceEditionActivity;
import nucleus.presenter.Presenter;

/**
 * Presenter for the dice edition
 */
public class DiceEditionPresenter extends Presenter<DiceEditionActivity>{

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        createNewDice();
        this.helper = SmartGmApplication.createDataBaseHandler();
        //No restore, no need backup on process fail
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.helper.close();
    }


    @Override
    protected void onTakeView(DiceEditionActivity activity) {
        super.onTakeView(activity);
        publish();
    }

    @Override
    protected void onDropView() {
        super.onDropView();

        dice.setFace(getView().getNbFace());
        dice.setCount(getView().getNbDice());
        fkUniverse = getView().getUniverse();

        if(fkUniverse != null && getView().isSaveToDatabase()){
            helper.getSession().getDiceDao().insertOrReplace(dice);
            dice.setUniverse(fkUniverse);
            helper.getSession().getDiceDao().update(dice);
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
        List<Universe> listUniverse = helper.getSession().getUniverseDao().loadAll();
        getView().setUniverseList(listUniverse);

        loadDice(getView().getId());
        getView().setNbDice(dice.getCount());
        getView().setNbFace(dice.getFace());
        getView().setUniverse(fkUniverse);
    }

    private void loadDice(long id) {
        if(id == Constants.INVALID_ID) {
            if (dice.getId() != null){
                createNewDice();
                fkUniverse = null;
            }
        } else {
            if (dice.getId() == null || id != dice.getId()) {
                dice = helper.getSession().getDiceDao().load(id);
                fkUniverse = dice.getUniverse();
            }
        }
    }

    private void createNewDice(){
        dice = new Dice();
        dice.setCount(1);
        dice.setFace(20);
        dice.setFk_universe_id(null);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataBaseHandler helper;
    private Dice dice;
    private Universe fkUniverse;

}
