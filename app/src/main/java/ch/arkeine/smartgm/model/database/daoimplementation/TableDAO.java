package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.TableDAOInterface;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseTableTable;
import ch.arkeine.smartgm.model.object.Table;
import ch.arkeine.smartgm.model.object.TableItem;

/**
 * Created by arkeine on 11/9/15.
 */
public class TableDAO extends DataBaseDAO<Table> implements TableDAOInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableDAO(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return DataBaseTableTable.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DataBaseTableTable.COLUMN_ID;
    }

    @Override
    public Table createObject(Cursor cursor) {
        Table obj = new Table(cursor.getLong(0), cursor.getLong(1), cursor.getString(2));
        return obj;
    }

    @Override
    public ContentValues getContentValue(Table obj) {
        ContentValues values = new ContentValues();
        values.put(DataBaseTableTable.COLUMN_NAME, obj.getName());
        values.put(DataBaseTableTable.COLUMN_GAME, obj.getGameId());
        return values;
    }

    @Override
    public String orderByColumn() {
        return DataBaseTableTable.COLUMN_NAME;
    }

    @Override
    public List<Table> listTable(long gameId) {
        final String WHERE_GAME_EQUALS = DataBaseTableTable.COLUMN_GAME + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_GAME_EQUALS, new String[]{String.valueOf(gameId)},
                null, null, orderByColumn());

        List<Table> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public Table saveRelatedData(Table obj) {

        TableItemDAO itemDAO = new TableItemDAO(getContext());

        itemDAO.removeAllTableId(obj.getId());

        for (TableItem item : obj.getItemList()) {
            item.setTableId(obj.getId());
            itemDAO.create(item);
        }
        return obj;
    }

    @Override
    public Table loadRelatedData(Table obj) {

        TableItemDAO itemDAO = new TableItemDAO(getContext());

        for (TableItem item : itemDAO.listTableItem(obj.getId())) {
            obj.add(item);
        }
        return obj;
    }
}
