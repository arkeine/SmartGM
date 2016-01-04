package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Table;
import ch.arkeine.smartgm.model.dao.object.TableItem;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TablePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.TableTableContract;

/**
 * Data layer access object. Can store a table object in the database.
 * (Provide all CRUD operations)
 */
public class TablePersistence extends ObjectWithIdentifierPersistence<Table>
        implements TablePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TablePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return TableTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return TableTableContract.COLUMN_ID;
    }

    @Override
    public Table createObject(Cursor cursor) {
        Table obj = new Table(cursor.getLong(0), cursor.getLong(1));
        obj.setName( cursor.getString(2));
        obj.setDescription(cursor.getString(3));
        return obj;
    }

    @Override
    public ContentValues createContentValue(Table obj) {
        ContentValues values = new ContentValues();
        values.put(TableTableContract.COLUMN_NAME, obj.getName());
        values.put(TableTableContract.COLUMN_UNIVERSE, obj.getUniverseId());
        values.put(TableTableContract.COLUMN_NAME, obj.getName());
        values.put(TableTableContract.COLUMN_DESCRIPTION, obj.getDescription());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return TableTableContract.COLUMN_NAME;
    }

    @Override
    public List<Table> listTable(long universeId) {
        final String WHERE_PARENT_EQUALS = TableTableContract.COLUMN_UNIVERSE + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_PARENT_EQUALS, new String[]{String.valueOf(universeId)},
                null, null, getColumnOrderBy());

        List<Table> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public Table saveRelatedData(Table obj) {

        TableItemPersistence itemDAO = new TableItemPersistence(getContext());

        itemDAO.removeAllTableItem(obj.getId());

        for (TableItem item : obj.getItemList()) {
            item.setTableId(obj.getId());
            itemDAO.create(item);
        }
        return obj;
    }

    @Override
    public Table loadRelatedData(Table obj) {

        TableItemPersistence itemDAO = new TableItemPersistence(getContext());

        for (TableItem item : itemDAO.listTableItem(obj.getId())) {
            obj.getItemList().add(item);
        }
        return obj;
    }
}
