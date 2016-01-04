package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.persistenceinterface.PersistenceInterface;

/**
 * Data layer access object. Can store a generic object with ID in the database.
 * (Provide all CRUD operations)
 */
public abstract class ObjectWithIdentifierPersistence<T extends ObjectWithIdentifier>
        extends DataBasePersistence implements PersistenceInterface<T> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ObjectWithIdentifierPersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // ABSTRACT
    /* ============================================ */

    protected abstract String getTableName();
    protected abstract String getColumnId();

    protected abstract T createObject(Cursor cursor);
    protected abstract ContentValues createContentValue(T obj);

    /* ============================================ */
    // SHOULD BE OVERRIDE
    /* ============================================ */

    protected String getColumnOrderBy(){return null;}
    protected T saveRelatedData(T obj){return obj;}
    protected T loadRelatedData(T obj){return obj;}

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public long[][] listId() {
        Cursor cursor = db.query(getTableName(),new String[]{getColumnId()},
                null, null, null, null, null);

        long[][] tabId = new long[cursor.getCount()][1];

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); ++i) {

                tabId[i][0] = cursor.getInt(0);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return tabId;
    }

    @Override
    public T find(long... id) {
        final String WHERE_ID_EQUALS = createWhereIdClause();

        if(id.length != 1) return null;

        Cursor cursor = db.query(getTableName(), null, WHERE_ID_EQUALS,
                new String[]{String.valueOf(id[0])}, null, null, null);

        T obj = null;
        if (cursor.moveToFirst()) {
            obj = createObject(cursor);
            loadRelatedData(obj);
        }

        cursor.close();
        return obj;

    }


    @Override
    public T create(T obj) {
        long id = db.insert(getTableName(), null, createContentValue(obj));

        obj.unlockIdentifier();
        obj.setId(id);
        obj.lockIdentifier();

        saveRelatedData(obj);

        return obj;
    }

    @Override
    public T update(T obj) {
        final String WHERE_ID_EQUALS = createWhereIdClause();

        db.update(getTableName(), createContentValue(obj), WHERE_ID_EQUALS,
                new String[]{String.valueOf(obj.getId())});
        saveRelatedData(obj);

        return obj;
    }

    @Override
    public void delete(T obj) {
        final String WHERE_ID_EQUALS = createWhereIdClause();

        db.delete(getTableName(), WHERE_ID_EQUALS,
                new String[]{String.valueOf(obj.getId())});
    }

    /* ============================================ */
    // TOOL
    /* ============================================ */

    protected List<T> createListFromCursor(Cursor cursor)
    {
        List<T> list = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                T obj = createObject(cursor);
                loadRelatedData(obj);
                list.add(obj);
            } while (cursor.moveToNext());
        }

        return list;
    }

    /* ============================================ */
    // PROTECTED
    /* ============================================ */

    protected String createWhereIdClause()
    {
        return getColumnId()+ " =?";
    }

    protected String revertOrder(){return " DESC ";}
}
