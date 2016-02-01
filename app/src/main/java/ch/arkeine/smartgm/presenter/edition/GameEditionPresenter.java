package ch.arkeine.smartgm.presenter.edition;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.Game;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.handler.DataBaseHandler;
import ch.arkeine.smartgm.view.activity.editiondb.GameEditionActivity;
import nucleus.presenter.Presenter;

/**
 * Presenter for the universe edition
 */
public class GameEditionPresenter extends Presenter<GameEditionActivity>{

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = new Game();
        this.helper = SmartGmApplication.createDataBaseHandler();
        //No restore, no need backup on process fail
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.helper.close();
    }


    @Override
    protected void onTakeView(GameEditionActivity universeEditionActivity) {
        super.onTakeView(universeEditionActivity);
        publish();
    }

    @Override
    protected void onDropView() {
        super.onDropView();

        game.setName(getView().getName());
        game.setDescription(getView().getDescription());
        fkUniverse = getView().getUniverse();

        if(fkUniverse != null && getView().isSaveToDatabase()){
            helper.getSession().getGameDao().insertOrReplace(game);
            game.setUniverse(fkUniverse);
            helper.getSession().getGameDao().update(game);
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

        loadGame(getView().getId());
        getView().setName(game.getName());
        getView().setDescription(game.getDescription());
        getView().setUniverse(fkUniverse);
    }

    private void loadGame(long gameId) {
        if(gameId == Constants.INVALID_ID) {
            if (game.getId() != null){
                game = new Game();
                fkUniverse = null;
            }
        } else {
            if (game.getId() == null || gameId != game.getId()) {
                game = helper.getSession().getGameDao().load(gameId);
                fkUniverse = game.getUniverse();
            }
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataBaseHandler helper;
    private Game game;
    private Universe fkUniverse;

}
