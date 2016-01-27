package ch.arkeine.smartgm.model;

import java.util.List;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "GAMES".
 */
public class Games {

    private Long id;
    private String Name;
    private String description;
    private Long fk_universe_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient GamesDao myDao;

    private Universes universes;
    private Long universes__resolvedKey;

    private List<Timelines> timelines;

    public Games() {
    }

    public Games(Long id) {
        this.id = id;
    }

    public Games(Long id, String Name, String description, Long fk_universe_id) {
        this.id = id;
        this.Name = Name;
        this.description = description;
        this.fk_universe_id = fk_universe_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGamesDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFk_universe_id() {
        return fk_universe_id;
    }

    public void setFk_universe_id(Long fk_universe_id) {
        this.fk_universe_id = fk_universe_id;
    }

    /** To-one relationship, resolved on first access. */
    public Universes getUniverses() {
        Long __key = this.fk_universe_id;
        if (universes__resolvedKey == null || !universes__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UniversesDao targetDao = daoSession.getUniversesDao();
            Universes universesNew = targetDao.load(__key);
            synchronized (this) {
                universes = universesNew;
            	universes__resolvedKey = __key;
            }
        }
        return universes;
    }

    public void setUniverses(Universes universes) {
        synchronized (this) {
            this.universes = universes;
            fk_universe_id = universes == null ? null : universes.getId();
            universes__resolvedKey = fk_universe_id;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Timelines> getTimelines() {
        if (timelines == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TimelinesDao targetDao = daoSession.getTimelinesDao();
            List<Timelines> timelinesNew = targetDao._queryGames_Timelines(id);
            synchronized (this) {
                if(timelines == null) {
                    timelines = timelinesNew;
                }
            }
        }
        return timelines;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetTimelines() {
        timelines = null;
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
