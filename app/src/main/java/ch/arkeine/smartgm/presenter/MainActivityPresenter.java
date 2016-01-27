package ch.arkeine.smartgm.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.arkeine.smartgm.MainFilterManager;
import ch.arkeine.smartgm.model.dao.object.Dice;
import ch.arkeine.smartgm.model.dao.object.Entity;
import ch.arkeine.smartgm.model.dao.object.Game;
import ch.arkeine.smartgm.model.dao.object.Table;
import ch.arkeine.smartgm.model.dao.object.TimeLine;
import ch.arkeine.smartgm.model.dao.object.Universe;
import ch.arkeine.smartgm.model.dao.object.WikiPage;
import ch.arkeine.smartgm.model.dao.persistenceinterface.DicePersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.EntityPersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.GamePersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TablePersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TimeLinePersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.UniversePersistenceInterface;
import ch.arkeine.smartgm.model.dao.persistenceinterface.WikiPagePersistenceInterface;
import ch.arkeine.smartgm.view.MainActivity;

/**
 * Presenter for the main activity which list multi-content
 */
public class MainActivityPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    /**
     * Create a new MainActivityPresenter with all the DAO objects needed
     */
    public MainActivityPresenter(MainFilterManager selectorManager,
                                 UniversePersistenceInterface universeDAO,
                                 GamePersistenceInterface gameDAO,
                                 DicePersistenceInterface diceDAO,
                                 WikiPagePersistenceInterface wikiDAO,
                                 TablePersistenceInterface tableDAO,
                                 TimeLinePersistenceInterface timeLineDAO,
                                 EntityPersistenceInterface entityDAO) {
        this.universeDAO = universeDAO;
        this.gameDAO = gameDAO;
        this.diceDAO = diceDAO;
        this.wikiDAO = wikiDAO;
        this.tableDAO = tableDAO;
        this.timeLineDAO = timeLineDAO;
        this.entityDAO = entityDAO;

        this.type = DATA_TYPE.UNIVERSE;
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

            case UNIVERSE:
                Universe u = (Universe) itemAtPosition;

                if (u.getId() == filter.getUniverseId()) {
                    filter.setUniverseId(null);
                }
                universeDAO.delete(u);
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
            case WIKI:
                wikiDAO.delete((WikiPage) itemAtPosition);
                break;
            case TABLE:
                tableDAO.delete((Table) itemAtPosition);
                break;
            case TIME_LINE:
                timeLineDAO.delete((TimeLine) itemAtPosition);
                break;
            case ENTITY_MODEL:
                entityDAO.delete((Entity) itemAtPosition);
                break;
        }
        publish();
    }

    public String getGameName(long gameId)
    {
        return gameDAO.find(gameId).getName();
    }

    public String getCharacterName(long gameId)
    {
        return "";//characterDAO.find(gameId).getName();
    }

    public String getUniverseName(long universeId) {
        return universeDAO.find(universeId).getName();
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

                case UNIVERSE:
                    l = universeDAO.listAll();
                    break;

                case GAME:
                    l = filter.isUniverseSelected() ?
                            gameDAO.listGame(filter.getUniverseId()) : new ArrayList<>(0);
                    break;
                case DICE:
                    l = filter.isUniverseSelected() ?
                            diceDAO.listDice(filter.getUniverseId()) : new ArrayList<>(0);
                    break;
                case WIKI:
                    l = filter.isUniverseSelected() ?
                            wikiDAO.listWikiPage(filter.getUniverseId()) : new ArrayList<>(0);
                    break;
                case TABLE:
                    l = filter.isUniverseSelected() ?
                            tableDAO.listTable(filter.getUniverseId()) : new ArrayList<>(0);
                    break;
                case TIME_LINE:
                    l = filter.isGameSelected() ?
                            timeLineDAO.listTimeLine(filter.getGameId()) : new ArrayList<>(0);
                    break;
                case ENTITY_MODEL:
                    l = filter.isUniverseSelected() ?
                            entityDAO.listItemByType(filter.getUniverseId(), Entity.Type.MODEL) : new ArrayList<>(0);
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

    private final UniversePersistenceInterface universeDAO;
    private final GamePersistenceInterface gameDAO;
    private final DicePersistenceInterface diceDAO;
    private final WikiPagePersistenceInterface wikiDAO;
    private final TablePersistenceInterface tableDAO;
    private final TimeLinePersistenceInterface timeLineDAO;
    private final EntityPersistenceInterface entityDAO;

    public enum DATA_TYPE {UNIVERSE, GAME, DICE, TABLE, WIKI, TIME_LINE, ENTITY_MODEL}
}
