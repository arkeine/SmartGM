package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.Game;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.persistenceinterface.GamePersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.UniversePersistenceInterface;
import ch.arkeine.smartgm.view.activity.DataEditionActivity;
import ch.arkeine.smartgm.view.GameEditionActivity;


/**
 * Presenter for the game edition
 */
public class GameEditionPresenter extends DescribableEditionPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public GameEditionPresenter(GamePersistenceInterface daoGame,
                                UniversePersistenceInterface daoUniverse) {
        super(daoGame);
        this.daoUniverse = daoUniverse;
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((Game) getObject()).getName();
        String note = ((Game) getObject()).getNote();

        ((GameEditionActivity) view).setName(name);
        ((GameEditionActivity) view).setNote(note);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Game(Constants.INVALID_ID, Constants.INVALID_ID);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setNote(String s) {
        ((Game) getObject()).setNote(s);
    }

    public void setUniverseId(long universeId) {
        ((Game) getObject()).setUniverseId(universeId);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private UniversePersistenceInterface daoUniverse;
}
