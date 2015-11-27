package ch.arkeine.smartgm.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.arkeine.smartgm.MainFilterManager;
import ch.arkeine.smartgm.model.dao.CharacterDAOInterface;
import ch.arkeine.smartgm.model.dao.DiceDAOInterface;
import ch.arkeine.smartgm.model.dao.GameDAOInterface;
import ch.arkeine.smartgm.model.dao.StatisticDAOInterface;
import ch.arkeine.smartgm.model.dao.TableDAOInterface;
import ch.arkeine.smartgm.model.database.daoimplementation.CharacterDAO;
import ch.arkeine.smartgm.model.object.*;
import ch.arkeine.smartgm.view.MainActivity;
import ch.arkeine.smartgm.model.object.Character;

/**
 * Created by Arkeine on 06.11.2015.
 */
public class MainActivityPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Create a new MainActivityPresenter with all the DAO objects needed
     */
    public MainActivityPresenter(
            GameDAOInterface gameDAO,
            DiceDAOInterface diceDAO,
            TableDAOInterface tableDAO,
            CharacterDAOInterface characterDAO,
            StatisticDAOInterface statisticDAO,
            MainFilterManager selectorManager) {
        this.gameDAO = gameDAO;
        this.diceDAO = diceDAO;
        this.tableDAO = tableDAO;
        this.characterDAO = characterDAO;
        this.statisticDAO = statisticDAO;
        this.type = DATA_TYPE.GAME;
        this.filter = selectorManager;
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    /**
     * @return Current data type which is displayed in view
     */
    public DATA_TYPE getType() {
        return type;
    }

    /**
     * Change the data type which is displayed in view
     */
    public void setType(DATA_TYPE type) {
        this.type = type;
        publish();
    }

    /**
     * This method is called by the view to register with the presenter
     *
     * @param view the view that the presenter will update
     */
    public void onTakeView(MainActivity view) {
        this.view = view;
        publish();
    }

    /**
     * Remove a object at the given index
     */
    public void remove(Object itemAtPosition) {
        //Select the type of data
        switch (type) {

            case GAME:
                Game g = (Game) itemAtPosition;

                if (g.getId() == filter.getGameId()) {
                    filter.setGameId(null);
                }
                gameDAO.delete(g);
                break;
            case DICE:
                diceDAO.delete((Dice) itemAtPosition);
                break;
            case TABLE:
                tableDAO.delete((Table) itemAtPosition);
                break;
            case CHARACTER:

                Character c = (Character) itemAtPosition;

                if (c.getId() == filter.getCharacterId()) {
                    filter.setCharacterId(null);
                }
                characterDAO.delete(c);
                break;
            case STATISTIC:
                statisticDAO.delete((Statistic) itemAtPosition);
                break;
        }
        publish();
    }

    public String getGameName(long gameId)
    {
        return gameDAO.find(gameId).getTitle();
    }

    public String getCharacterName(long gameId)
    {
        return characterDAO.find(gameId).getName();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /**
     * Load the data from model to the view
     */
    private void publish() {
        if (view != null) {

            List l = null;
            switch (type) {

                case GAME:
                    l = gameDAO.listAll();
                    break;
                case DICE:
                    l = filter.isGameSelected() ?
                            diceDAO.listDice(filter.getGameId()) : new ArrayList<>(0);
                    break;
                case TABLE:
                    l = filter.isGameSelected() ?
                            tableDAO.listTable(filter.getGameId()) : new ArrayList<>(0);
                    break;
                case CHARACTER:
                    l = filter.isGameSelected() ?
                            characterDAO.listCharacter(filter.getGameId()) : new ArrayList<>(0);
                    break;
                case STATISTIC:
                    l = filter.isGameSelected() ?
                            statisticDAO.listStatistic(filter.getCharacterId()) : new ArrayList<>(0);
                    break;
            }
            view.setListContent(type, l);
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DATA_TYPE type;
    private MainActivity view;
    private final MainFilterManager filter;

    private final GameDAOInterface gameDAO;
    private final DiceDAOInterface diceDAO;
    private final TableDAOInterface tableDAO;
    private final CharacterDAOInterface characterDAO;
    private final StatisticDAOInterface statisticDAO;

    public enum DATA_TYPE {GAME, DICE, TABLE, CHARACTER, STATISTIC}
}
