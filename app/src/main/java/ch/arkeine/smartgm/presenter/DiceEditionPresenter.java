package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.Dice;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.persistenceinterface.DicePersistenceInterface;
import ch.arkeine.smartgm.view.activity.DataEditionActivity;
import ch.arkeine.smartgm.view.DiceEditionActivity;


/**
 * Presenter for the dice edition
 */
public class DiceEditionPresenter extends DataEditionPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DiceEditionPresenter(DicePersistenceInterface diceDAO) {
        super(diceDAO);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        int nbFace = ((Dice) getObject()).getNbFaces();
        ((DiceEditionActivity) view).setNbFace(nbFace);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Dice(Constants.INVALID_ID, Constants.INVALID_ID, 0);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setNbFace(int nbFace) {
        ((Dice) getObject()).setNbFaces(nbFace);
    }

    public void setUniverseId(long universeId) {
        ((Dice) getObject()).setUniverseId(universeId);
    }

}
