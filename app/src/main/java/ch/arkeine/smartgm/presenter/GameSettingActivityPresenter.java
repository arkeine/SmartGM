package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.GameDAOInterface;
import ch.arkeine.smartgm.model.object.Game;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.view.DataEditionActivity;
import ch.arkeine.smartgm.view.GameSettingActivity;


/**
 * Created by Arkeine on 12.11.2015.
 */
public class GameSettingActivityPresenter extends DataEditionActivityPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public GameSettingActivityPresenter(GameDAOInterface gameDAO) {

        super(gameDAO);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String title = ((Game) getObject()).getTitle();
        String description = ((Game) getObject()).getDescription();
        ((GameSettingActivity) view).setTitle(title);
        ((GameSettingActivity) view).setDescription(description);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Game(Constants.invalidId);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setGameTitle(String title) {
        ((Game) getObject()).setTitle(title);
    }

    public void setGameDesciption(String description) {
        ((Game) getObject()).setDescription(description);
    }
}
