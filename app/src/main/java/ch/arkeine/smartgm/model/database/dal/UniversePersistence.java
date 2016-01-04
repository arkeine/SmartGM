package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Universe;
import ch.arkeine.smartgm.model.dao.persistenceinterface.UniversePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.UniverseTableContract;

/**
 * Data layer access object. Can store a universe object in the database.
 * (Provide all CRUD operations)
 */
public class UniversePersistence extends ObjectWithIdentifierPersistence<Universe>
        implements UniversePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public UniversePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected String getTableName() {
        return UniverseTableContract.TABLE_NAME;
    }

    @Override
    protected String getColumnId() {
        return UniverseTableContract.COLUMN_ID;
    }

    @Override
    protected Universe createObject(Cursor cursor) {
        Universe obj =  new Universe(cursor.getLong(0));
        obj.setName(cursor.getString(1));
        obj.setDescription(cursor.getString(2));
        return obj;
    }

    @Override
    protected ContentValues createContentValue(Universe obj) {
        ContentValues values = new ContentValues();
        values.put(UniverseTableContract.COLUMN_NAME, obj.getName());
        values.put(UniverseTableContract.COLUMN_DESCRIPTION, obj.getDescription());
        return values;
    }

    @Override
    public List<Universe> listAll() {
        Cursor cursor = db.query(getTableName(), null,
                null, null, null, null, getColumnOrderBy());

        return createListFromCursor(cursor);
    }

    @Override
    protected String getColumnOrderBy() {
        return UniverseTableContract.COLUMN_NAME;
    }
}
