package ch.arkeine.smartgm.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TABLEITEMS".
*/
public class TableitemsDao extends AbstractDao<Tableitems, Long> {

    public static final String TABLENAME = "TABLEITEMS";

    /**
     * Properties of entity Tableitems.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, java.util.Date.class, "name", false, "NAME");
        public final static Property Description = new Property(2, String.class, "description", false, "DESCRIPTION");
        public final static Property Weight = new Property(3, Integer.class, "weight", false, "WEIGHT");
        public final static Property Fk_table_id = new Property(4, Long.class, "fk_table_id", false, "FK_TABLE_ID");
    };

    private DaoSession daoSession;

    private Query<Tableitems> tables_TableitemQuery;

    public TableitemsDao(DaoConfig config) {
        super(config);
    }
    
    public TableitemsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TABLEITEMS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" INTEGER," + // 1: name
                "\"DESCRIPTION\" TEXT," + // 2: description
                "\"WEIGHT\" INTEGER," + // 3: weight
                "\"FK_TABLE_ID\" INTEGER);"); // 4: fk_table_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TABLEITEMS\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Tableitems entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date name = entity.getName();
        if (name != null) {
            stmt.bindLong(2, name.getTime());
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(3, description);
        }
 
        Integer weight = entity.getWeight();
        if (weight != null) {
            stmt.bindLong(4, weight);
        }
 
        Long fk_table_id = entity.getFk_table_id();
        if (fk_table_id != null) {
            stmt.bindLong(5, fk_table_id);
        }
    }

    @Override
    protected void attachEntity(Tableitems entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Tableitems readEntity(Cursor cursor, int offset) {
        Tableitems entity = new Tableitems( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // description
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // weight
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // fk_table_id
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Tableitems entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setDescription(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWeight(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setFk_table_id(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Tableitems entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Tableitems entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "tableitem" to-many relationship of Tables. */
    public List<Tableitems> _queryTables_Tableitem(Long fk_table_id) {
        synchronized (this) {
            if (tables_TableitemQuery == null) {
                QueryBuilder<Tableitems> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Fk_table_id.eq(null));
                tables_TableitemQuery = queryBuilder.build();
            }
        }
        Query<Tableitems> query = tables_TableitemQuery.forCurrentThread();
        query.setParameter(0, fk_table_id);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getTablesDao().getAllColumns());
            builder.append(" FROM TABLEITEMS T");
            builder.append(" LEFT JOIN TABLES T0 ON T.\"FK_TABLE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Tableitems loadCurrentDeep(Cursor cursor, boolean lock) {
        Tableitems entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Tables tables = loadCurrentOther(daoSession.getTablesDao(), cursor, offset);
        entity.setTables(tables);

        return entity;    
    }

    public Tableitems loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Tableitems> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Tableitems> list = new ArrayList<Tableitems>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Tableitems> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Tableitems> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
