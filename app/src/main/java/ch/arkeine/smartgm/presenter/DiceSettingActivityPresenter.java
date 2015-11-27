package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.DiceDAOInterface;
import ch.arkeine.smartgm.model.object.Dice;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.view.DataEditionActivity;
import ch.arkeine.smartgm.view.DiceSettingActivity;


/**
 * Created by Arkeine on 12.11.2015.
 */
public class DiceSettingActivityPresenter extends DataEditionActivityPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DiceSettingActivityPresenter(DiceDAOInterface diceDAO) {
        super(diceDAO);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        int nbFace = ((Dice) getObject()).getNbFaces();
        ((DiceSettingActivity) view).setNbFace(nbFace);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Dice(Constants.invalidId, Constants.invalidId, 0);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setNbFace(int nbFace) {
        ((Dice) getObject()).setNbFaces(nbFace);
    }

    public void setGameId(long gameId) {
        ((Dice) getObject()).setGameId(gameId);
    }

}
