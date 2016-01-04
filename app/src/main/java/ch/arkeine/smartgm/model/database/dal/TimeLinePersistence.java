package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.arkeine.smartgm.model.dao.object.TimeLine;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TimeLinePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.TimeLineTableContract;

/**
 * Data layer access object. Can store a time line object in the database.
 * (Provide all CRUD operations)
 */
public class TimeLinePersistence extends ObjectWithIdentifierPersistence<TimeLine>
        implements TimeLinePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TimeLinePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return TimeLineTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return TimeLineTableContract.COLUMN_ID;
    }

    @Override
    public TimeLine createObject(Cursor cursor) {
        TimeLine obj = new TimeLine(cursor.getLong(0), cursor.getLong(1));
        obj.setName(cursor.getString(2));
        obj.setDescription(cursor.getString(3));
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(cursor.getLong(4));
        obj.setDate(c);
        return obj;
    }

    @Override
    public ContentValues createContentValue(TimeLine obj) {
        ContentValues values = new ContentValues();
        values.put(TimeLineTableContract.COLUMN_NAME, obj.getName());
        values.put(TimeLineTableContract.COLUMN_GAME, obj.getGameId());
        values.put(TimeLineTableContract.COLUMN_DATE, obj.getDate().getTimeInMillis());
        values.put(TimeLineTableContract.COLUMN_DESCRIPTION, obj.getDescription());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return TimeLineTableContract.COLUMN_DATE + revertOrder();
    }

    @Override
    public List<TimeLine> listTimeLine(long universeId) {
        final String WHERE_PARENT_EQUALS = TimeLineTableContract.COLUMN_GAME + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_PARENT_EQUALS, new String[]{String.valueOf(universeId)},
                null, null, getColumnOrderBy());

        List<TimeLine> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }
}
