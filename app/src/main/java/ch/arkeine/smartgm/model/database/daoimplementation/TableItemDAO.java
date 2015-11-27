package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.TableItemDAOInterface;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseTableItemTable;
import ch.arkeine.smartgm.model.object.TableItem;

/**
 * Created by arkeine on 11/9/15.
 */
public class TableItemDAO extends DataBaseDAO<TableItem> implements TableItemDAOInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableItemDAO(Context context) {
        super(context);
    }

    /* ============================================ */
    // CRUD
    /* ============================================ */

    @Override
    public String getTableName() {
        return DataBaseTableItemTable.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DataBaseTableItemTable.COLUMN_ID;
    }

    @Override
    public TableItem createObject(Cursor cursor) {
        return new TableItem(cursor.getLong(0), cursor.getLong(1), cursor.getString(2),
                        cursor.getString(3), cursor.getInt(4));
    }

    @Override
    public ContentValues getContentValue(TableItem obj) {
        ContentValues values = new ContentValues();
        values.put(DataBaseTableItemTable.COLUMN_TABLE, obj.getTableId());
        values.put(DataBaseTableItemTable.COLUMN_TITLE, obj.getTitle());
        values.put(DataBaseTableItemTable.COLUMN_DESCRIPTION, obj.getDescription());
        values.put(DataBaseTableItemTable.COLUMN_WEIGHT, obj.getWeight());
        return values;
    }

    @Override
    public String orderByColumn() {
        return DataBaseTableItemTable.COLUMN_WEIGHT;
    }

    @Override
    public List<TableItem> listTableItem(long tableId) {
        final String WHERE_TABLE_EQUALS = DataBaseTableItemTable.COLUMN_TABLE + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_TABLE_EQUALS, new String[]{String.valueOf(tableId)},
                null, null,orderByColumn());

        List<TableItem> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public void removeAllTableId(long tableId) {
        final String WHERE_TABLE_EQUALS = DataBaseTableItemTable.COLUMN_TABLE + " =?";

        //Remove previous item own by table
        System.out.println("SASDSAD"+db.delete(getTableName(), WHERE_TABLE_EQUALS,
                new String[]{String.valueOf(tableId)}));
    }
}
