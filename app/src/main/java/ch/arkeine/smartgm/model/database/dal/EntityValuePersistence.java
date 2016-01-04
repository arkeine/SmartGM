package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.EntityValue;
import ch.arkeine.smartgm.model.dao.persistenceinterface.EntityValuePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.EntityValueTableContract;

/**
 * Data layer access object. Can store a table item object in the database.
 * (Provide all CRUD operations)
 */
public class EntityValuePersistence extends ObjectWithIdentifierPersistence<EntityValue> implements EntityValuePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public EntityValuePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return EntityValueTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return EntityValueTableContract.COLUMN_ID;
    }

    @Override
    public EntityValue createObject(Cursor cursor) {
        EntityValue obj = new EntityValue(cursor.getLong(0), cursor.getLong(1));

        obj.setName(cursor.getString(2));
        obj.setValue(cursor.getInt(3));
        return obj;
    }

    @Override
    public ContentValues createContentValue(EntityValue obj) {
        ContentValues values = new ContentValues();
        values.put(EntityValueTableContract.COLUMN_ENTITY, obj.getEntityId());
        values.put(EntityValueTableContract.COLUMN_NAME, obj.getName());
        values.put(EntityValueTableContract.COLUMN_VALUE, obj.getValue());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return EntityValueTableContract.COLUMN_NAME;
    }

    @Override
    public List<EntityValue> listEntityValue(long entityId) {
        final String WHERE_ENTITY_EQUALS = createWhereIdClause();

        Cursor cursor = db.query(getTableName(), null,
                WHERE_ENTITY_EQUALS, new String[]{String.valueOf(entityId)},
                null, null, getColumnOrderBy());

        List<EntityValue> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public void removeAllEntityValue(long entityId) {
        final String WHERE_ENTITY_EQUALS = createWhereIdClause();

        //Remove previous item own by entity
        db.delete(getTableName(), WHERE_ENTITY_EQUALS, new String[]{String.valueOf(entityId)});
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    protected String createWhereIdClause(){return EntityValueTableContract.COLUMN_ENTITY + " =?";}

}
