package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.DAOInterface;
import ch.arkeine.smartgm.model.object.Character;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.view.DataEditionActivity;

/**
 * Created by arkeine on 11/26/15.
 */
public class CharacterSettingActivityPresenter extends DataEditionActivityPresenter{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public CharacterSettingActivityPresenter(DAOInterface dao) {
        super(dao);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {

    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Character(Constants.invalidId, Constants.invalidId);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setGameId(long gameId) {
        ((Character) getObject()).setGameId(gameId);
    }

    public void setName(String name) {
        ((Character) getObject()).setName(name);
    }
}
