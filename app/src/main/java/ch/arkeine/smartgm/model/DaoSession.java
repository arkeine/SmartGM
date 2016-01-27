package ch.arkeine.smartgm.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig universeDaoConfig;
    private final DaoConfig gameDaoConfig;
    private final DaoConfig wikiDaoConfig;
    private final DaoConfig timelineDaoConfig;
    private final DaoConfig tableDaoConfig;
    private final DaoConfig tableitemDaoConfig;
    private final DaoConfig diceDaoConfig;

    private final UniverseDao universeDao;
    private final GameDao gameDao;
    private final WikiDao wikiDao;
    private final TimelineDao timelineDao;
    private final TableDao tableDao;
    private final TableitemDao tableitemDao;
    private final DiceDao diceDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        universeDaoConfig = daoConfigMap.get(UniverseDao.class).clone();
        universeDaoConfig.initIdentityScope(type);

        gameDaoConfig = daoConfigMap.get(GameDao.class).clone();
        gameDaoConfig.initIdentityScope(type);

        wikiDaoConfig = daoConfigMap.get(WikiDao.class).clone();
        wikiDaoConfig.initIdentityScope(type);

        timelineDaoConfig = daoConfigMap.get(TimelineDao.class).clone();
        timelineDaoConfig.initIdentityScope(type);

        tableDaoConfig = daoConfigMap.get(TableDao.class).clone();
        tableDaoConfig.initIdentityScope(type);

        tableitemDaoConfig = daoConfigMap.get(TableitemDao.class).clone();
        tableitemDaoConfig.initIdentityScope(type);

        diceDaoConfig = daoConfigMap.get(DiceDao.class).clone();
        diceDaoConfig.initIdentityScope(type);

        universeDao = new UniverseDao(universeDaoConfig, this);
        gameDao = new GameDao(gameDaoConfig, this);
        wikiDao = new WikiDao(wikiDaoConfig, this);
        timelineDao = new TimelineDao(timelineDaoConfig, this);
        tableDao = new TableDao(tableDaoConfig, this);
        tableitemDao = new TableitemDao(tableitemDaoConfig, this);
        diceDao = new DiceDao(diceDaoConfig, this);

        registerDao(Universe.class, universeDao);
        registerDao(Game.class, gameDao);
        registerDao(Wiki.class, wikiDao);
        registerDao(Timeline.class, timelineDao);
        registerDao(Table.class, tableDao);
        registerDao(Tableitem.class, tableitemDao);
        registerDao(Dice.class, diceDao);
    }
    
    public void clear() {
        universeDaoConfig.getIdentityScope().clear();
        gameDaoConfig.getIdentityScope().clear();
        wikiDaoConfig.getIdentityScope().clear();
        timelineDaoConfig.getIdentityScope().clear();
        tableDaoConfig.getIdentityScope().clear();
        tableitemDaoConfig.getIdentityScope().clear();
        diceDaoConfig.getIdentityScope().clear();
    }

    public UniverseDao getUniverseDao() {
        return universeDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }

    public WikiDao getWikiDao() {
        return wikiDao;
    }

    public TimelineDao getTimelineDao() {
        return timelineDao;
    }

    public TableDao getTableDao() {
        return tableDao;
    }

    public TableitemDao getTableitemDao() {
        return tableitemDao;
    }

    public DiceDao getDiceDao() {
        return diceDao;
    }

}
