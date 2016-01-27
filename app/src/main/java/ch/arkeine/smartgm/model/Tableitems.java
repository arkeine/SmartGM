package ch.arkeine.smartgm.model;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TABLEITEMS".
 */
public class Tableitems {

    private Long id;
    private java.util.Date name;
    private String description;
    private Integer weight;
    private Long fk_table_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TableitemsDao myDao;

    private Tables tables;
    private Long tables__resolvedKey;


    public Tableitems() {
    }

    public Tableitems(Long id) {
        this.id = id;
    }

    public Tableitems(Long id, java.util.Date name, String description, Integer weight, Long fk_table_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.fk_table_id = fk_table_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTableitemsDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getName() {
        return name;
    }

    public void setName(java.util.Date name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getFk_table_id() {
        return fk_table_id;
    }

    public void setFk_table_id(Long fk_table_id) {
        this.fk_table_id = fk_table_id;
    }

    /** To-one relationship, resolved on first access. */
    public Tables getTables() {
        Long __key = this.fk_table_id;
        if (tables__resolvedKey == null || !tables__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TablesDao targetDao = daoSession.getTablesDao();
            Tables tablesNew = targetDao.load(__key);
            synchronized (this) {
                tables = tablesNew;
            	tables__resolvedKey = __key;
            }
        }
        return tables;
    }

    public void setTables(Tables tables) {
        synchronized (this) {
            this.tables = tables;
            fk_table_id = tables == null ? null : tables.getId();
            tables__resolvedKey = fk_table_id;
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
