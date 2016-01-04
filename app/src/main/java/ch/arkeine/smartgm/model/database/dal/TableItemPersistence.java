package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.TableItem;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TableItemPersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.TableItemTableContract;

/**
 * Data layer access object. Can store a table item object in the database.
 * (Provide all CRUD operations)
 */
public class TableItemPersistence extends ObjectWithIdentifierPersistence<TableItem> implements TableItemPersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableItemPersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return TableItemTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return TableItemTableContract.COLUMN_ID;
    }

    @Override
    public TableItem createObject(Cursor cursor) {
        TableItem obj = new TableItem(cursor.getLong(0), cursor.getLong(1));

        obj.setName(cursor.getString(2));
        obj.setWeight(cursor.getInt(3));
        return obj;
    }

    @Override
    public ContentValues createContentValue(TableItem obj) {
        ContentValues values = new ContentValues();
        values.put(TableItemTableContract.COLUMN_TABLE, obj.getTableId());
        values.put(TableItemTableContract.COLUMN_NAME, obj.getName());
        values.put(TableItemTableContract.COLUMN_WEIGHT, obj.getWeight());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return TableItemTableContract.COLUMN_WEIGHT  + revertOrder();
    }

    @Override
    public List<TableItem> listTableItem(long tableId) {
        final String WHERE_TABLE_EQUALS = createWhereIdClause();

        Cursor cursor = db.query(getTableName(), null,
                WHERE_TABLE_EQUALS, new String[]{String.valueOf(tableId)},
                null, null, getColumnOrderBy());

        List<TableItem> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public void removeAllTableItem(long tableId) {
        final String WHERE_TABLE_EQUALS = createWhereIdClause();

        //Remove previous item own by table
        db.delete(getTableName(), WHERE_TABLE_EQUALS, new String[]{String.valueOf(tableId)});
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    protected String createWhereIdClause(){return TableItemTableContract.COLUMN_TABLE + " =?";}
}
