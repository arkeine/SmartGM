package ch.arkeine.smartgm.model;

import ch.arkeine.smartgm.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import ch.arkeine.smartgm.model.helper.IdentifiedDataObject;
// KEEP INCLUDES END
/**
 * Entity mapped to table "DICE".
 */
public class Dice implements IdentifiedDataObject {

    private Long id;
    private Integer face;
    private Integer count;
    private Long fk_universe_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient DiceDao myDao;

    private Universe universe;
    private Long universe__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Dice() {
    }

    public Dice(Long id) {
        this.id = id;
    }

    public Dice(Long id, Integer face, Integer count, Long fk_universe_id) {
        this.id = id;
        this.face = face;
        this.count = count;
        this.fk_universe_id = fk_universe_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDiceDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFace() {
        return face;
    }

    public void setFace(Integer face) {
        this.face = face;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getFk_universe_id() {
        return fk_universe_id;
    }

    public void setFk_universe_id(Long fk_universe_id) {
        this.fk_universe_id = fk_universe_id;
    }

    /** To-one relationship, resolved on first access. */
    public Universe getUniverse() {
        Long __key = this.fk_universe_id;
        if (universe__resolvedKey == null || !universe__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UniverseDao targetDao = daoSession.getUniverseDao();
            Universe universeNew = targetDao.load(__key);
            synchronized (this) {
                universe = universeNew;
            	universe__resolvedKey = __key;
            }
        }
        return universe;
    }

    public void setUniverse(Universe universe) {
        synchronized (this) {
            this.universe = universe;
            fk_universe_id = universe == null ? null : universe.getId();
            universe__resolvedKey = fk_universe_id;
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

    // KEEP METHODS - put your custom methods here
    @Override
    public long getUniqueId() {
        return getId();
    }

    @Override
    public String getListingText() {
        return this.getFace().toString();
    }
    // KEEP METHODS END

}
