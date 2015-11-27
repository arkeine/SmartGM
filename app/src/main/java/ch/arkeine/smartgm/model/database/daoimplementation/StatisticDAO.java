package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.StatisticDAOInterface;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseStatisticTable;
import ch.arkeine.smartgm.model.object.Statistic;

/**
 * Created by arkeine on 11/9/15.
 */
public class StatisticDAO extends DataBaseDAO<Statistic> implements StatisticDAOInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public StatisticDAO(Context context) {
        super(context);
    }

    /* ============================================ */
    // CRUD
    /* ============================================ */

    @Override
    public String getTableName() {
        return DataBaseStatisticTable.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DataBaseStatisticTable.COLUMN_ID;
    }

    @Override
    public Statistic createObject(Cursor cursor) {
        return new Statistic(cursor.getLong(0), cursor.getLong(1), cursor.getInt(2),
                cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
    }

    @Override
    public ContentValues getContentValue(Statistic obj) {
        ContentValues values = new ContentValues();
        values.put(DataBaseStatisticTable.COLUMN_CHARACTER, obj.getCharacterId());
        values.put(DataBaseStatisticTable.COLUMN_NAME, obj.getName());
        values.put(DataBaseStatisticTable.COLUMN_MIN, obj.getMin());
        values.put(DataBaseStatisticTable.COLUMN_CURRENT, obj.getCurrent());
        values.put(DataBaseStatisticTable.COLUMN_MAX, obj.getMax());
        return values;
    }

    @Override
    public String orderByColumn() {
        return DataBaseStatisticTable.COLUMN_NAME;
    }

    @Override
    public List<Statistic> listStatistic(long characterId) {
        final String WHERE_CHARACTER_EQUALS = DataBaseStatisticTable.COLUMN_CHARACTER + " =?";

        Cursor cursor = db.query(DataBaseStatisticTable.TABLE_NAME, null,
                WHERE_CHARACTER_EQUALS, new String[]{String.valueOf(characterId)},
                null, null, orderByColumn());

        List<Statistic> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public void removeAllCharacterId(long characterId) {
        final String WHERE_CHARACTER_EQUALS = DataBaseStatisticTable.COLUMN_CHARACTER + " =?";

        //Remove previous item own by table
        db.delete(getTableName(), WHERE_CHARACTER_EQUALS,
                new String[]{String.valueOf(characterId)});
    }
}
