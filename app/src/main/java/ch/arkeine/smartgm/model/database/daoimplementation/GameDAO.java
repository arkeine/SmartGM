package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ch.arkeine.smartgm.model.dao.GameDAOInterface;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseGameTable;
import ch.arkeine.smartgm.model.object.Game;

/**
 * Created by Arkeine on 08.11.2015.
 */
public class GameDAO extends DataBaseDAO<Game> implements GameDAOInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public GameDAO(Context context) {
        super(context);
    }

    /* ============================================ */
    // CRUD
    /* ============================================ */

    @Override
    public String getTableName() {
        return DataBaseGameTable.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DataBaseGameTable.COLUMN_ID;
    }

    @Override
    public Game createObject(Cursor cursor) {
        return new Game(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    @Override
    public ContentValues getContentValue(Game obj) {
        ContentValues values = new ContentValues();
        values.put(DataBaseGameTable.COLUMN_NAME, obj.getTitle());
        values.put(DataBaseGameTable.COLUMN_DESCRIPTION, obj.getDescription());
        return values;
    }

    @Override
    public String orderByColumn() {
        return DataBaseGameTable.COLUMN_NAME;
    }
}
