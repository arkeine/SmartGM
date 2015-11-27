package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ch.arkeine.smartgm.model.dao.DAOInterface;
import ch.arkeine.smartgm.model.database.DataBaseHelper;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;

/**
 * Created by Arkeine on 08.11.2015.
 */
public abstract class DataBaseDAO<T extends ObjectWithIdentifier> implements DAOInterface<T> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DataBaseDAO(Context context) {
        this.context = context;
        open();
    }

    /* ============================================ */
    // ABSTRACT
    /* ============================================ */

    public abstract String getTableName();
    public abstract String getColumnId();
    public abstract T createObject(Cursor cursor);
    public abstract ContentValues getContentValue(T obj);

    public String orderByColumn(){return null;}
    public T saveRelatedData(T obj){return obj;}
    public T loadRelatedData(T obj){return obj;}

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public long[] listId() {
        Cursor cursor = db.query(getTableName(),new String[]{getColumnId()},
                null, null, null, null, null);

        long[] tabId = new long[cursor.getCount()];

        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); ++i) {
                tabId[i] = cursor.getInt(0);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return tabId;
    }

    @Override
    public T find(long id) {
        final String WHERE_ID_EQUALS = getColumnId()+ " =?";

        Cursor cursor = db.query(getTableName(), null, WHERE_ID_EQUALS,
                new String[]{String.valueOf(id)}, null, null, null);

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
        long id = db.insert(getTableName(), null, getContentValue(obj));

        obj.unlockIdentifier();
        obj.setId(id);
        obj.lockIdentifier();

        saveRelatedData(obj);

        return obj;
    }

    @Override
    public T update(T obj) {
        final String WHERE_ID_EQUALS = getColumnId()+ " =?";

        db.update(getTableName(), getContentValue(obj), WHERE_ID_EQUALS,
                new String[]{String.valueOf(obj.getId())});
        saveRelatedData(obj);

        return obj;
    }

    @Override
    public void delete(T table) {
        final String WHERE_ID_EQUALS = getColumnId()+ " =?";

        db.delete(getTableName(), WHERE_ID_EQUALS,
                new String[]{String.valueOf(table.getId())});
    }

    @Override
    public List<T> listAll() {
        Cursor cursor = db.query(getTableName(), null,
                null, null, null, null, orderByColumn());

        return createListFromCursor(cursor);
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void open() throws SQLException {
        if (dbh == null)
            dbh = DataBaseHelper.getHelper(context);
        db = dbh.getWritableDatabase();
    }

    public void close() {
        dbh.close();
        db = null;
    }

    public Context getContext() {
        return context;
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
    // FIELD
    /* ============================================ */

    protected SQLiteDatabase db;
    private DataBaseHelper dbh;
    private Context context;

}
