package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Entity;
import ch.arkeine.smartgm.model.dao.object.EntityValue;
import ch.arkeine.smartgm.model.dao.persistenceinterface.EntityPersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.EntityTableContract;

/**
 * Data layer access object. Can store a item object in the database.
 * (Provide all CRUD operations)
 */
public class EntityPersistence extends ObjectWithIdentifierPersistence<Entity> implements EntityPersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public EntityPersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // CRUD
    /* ============================================ */

    @Override
    public String getTableName() {
        return EntityTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return EntityTableContract.COLUMN_ID;
    }

    @Override
    public Entity createObject(Cursor cursor) {
        Long gameID = cursor.isNull(2) ? null : cursor.getLong(2);
        Long parentID = cursor.isNull(3) ? null : cursor.getLong(3);

        Entity obj = new Entity(cursor.getLong(0), cursor.getLong(1), gameID);
        obj.setParentId(parentID);
        obj.setName(cursor.getString(4));
        obj.setDescription(cursor.getString(5));
        obj.setType(Entity.Type.values()[cursor.getInt(6)]);
        return obj;
    }

    @Override
    public ContentValues createContentValue(Entity obj) {
        ContentValues values = new ContentValues();
        values.put(EntityTableContract.COLUMN_NAME, obj.getName());
        values.put(EntityTableContract.COLUMN_GAME, obj.getGameId());
        values.put(EntityTableContract.COLUMN_PARENT, obj.getParentId());
        values.put(EntityTableContract.COLUMN_UNIVERSE, obj.getUniverseId());
        values.put(EntityTableContract.COLUMN_DESCRIPTION, obj.getDescription());
        values.put(EntityTableContract.COLUMN_TYPE, obj.getType().ordinal());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return EntityTableContract.COLUMN_NAME;
    }

    @Override
    public List<Entity> listItemByType(long universeId, Entity.Type type) {
        final String WHERE_PATTERN_EQUALS = EntityTableContract.COLUMN_UNIVERSE + " =? AND "+
                EntityTableContract.COLUMN_TYPE + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_PATTERN_EQUALS, new String[]{String.valueOf(universeId), String.valueOf(type.ordinal())},
                null, null, getColumnOrderBy());

        List<Entity> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public Entity saveRelatedData(Entity obj) {
        EntityValuePersistence valueDAO = new EntityValuePersistence(getContext());

        valueDAO.removeAllEntityValue(obj.getId());

        for (EntityValue item : obj.getListValues()) {
            item.setEntityId(obj.getId());
            valueDAO.create(item);
        }
        return obj;

    }

    @Override
    public Entity loadRelatedData(Entity obj) {
        EntityValuePersistence valueDAO = new EntityValuePersistence(getContext());

        for (EntityValue item : valueDAO.listEntityValue(obj.getId())) {
            obj.getListValues().add(item);
        }
        return obj;
    }
}
