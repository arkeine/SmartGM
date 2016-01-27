package ch.arkeine.smartgm.model;

import ch.arkeine.smartgm.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TIMELINE".
 */
public class Timeline {

    private Long id;
    private java.util.Date date;
    private String description;
    private Long fk_game_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TimelineDao myDao;

    private Game game;
    private Long game__resolvedKey;


    public Timeline() {
    }

    public Timeline(Long id) {
        this.id = id;
    }

    public Timeline(Long id, java.util.Date date, String description, Long fk_game_id) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.fk_game_id = fk_game_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTimelineDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFk_game_id() {
        return fk_game_id;
    }

    public void setFk_game_id(Long fk_game_id) {
        this.fk_game_id = fk_game_id;
    }

    /** To-one relationship, resolved on first access. */
    public Game getGame() {
        Long __key = this.fk_game_id;
        if (game__resolvedKey == null || !game__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameDao targetDao = daoSession.getGameDao();
            Game gameNew = targetDao.load(__key);
            synchronized (this) {
                game = gameNew;
            	game__resolvedKey = __key;
            }
        }
        return game;
    }

    public void setGame(Game game) {
        synchronized (this) {
            this.game = game;
            fk_game_id = game == null ? null : game.getId();
            game__resolvedKey = fk_game_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}