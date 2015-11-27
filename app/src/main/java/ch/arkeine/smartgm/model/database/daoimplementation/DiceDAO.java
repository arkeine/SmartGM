package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.DiceDAOInterface;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseDiceTable;
import ch.arkeine.smartgm.model.object.Dice;

/**
 * Created by arkeine on 11/9/15.
 */
public class DiceDAO extends DataBaseDAO<Dice> implements DiceDAOInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DiceDAO(Context context) {
        super(context);
    }

    /* ============================================ */
    // CRUD
    /* ============================================ */

    @Override
    public String getTableName() {
        return DataBaseDiceTable.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DataBaseDiceTable.COLUMN_ID;
    }

    @Override
    public Dice createObject(Cursor cursor) {
        return new Dice(cursor.getLong(0), cursor.getLong(1), cursor.getInt(2));
    }

    @Override
    public ContentValues getContentValue(Dice obj) {
        ContentValues values = new ContentValues();
        values.put(DataBaseDiceTable.COLUMN_NBFACE, obj.getNbFaces());
        values.put(DataBaseDiceTable.COLUMN_GAME, obj.getGameId());
        return values;
    }

    @Override
    public String orderByColumn() {
        return DataBaseDiceTable.COLUMN_NBFACE;
    }

    @Override
    public List<Dice> listDice(long gameId) {
        final String WHERE_GAME_EQUALS = DataBaseDiceTable.COLUMN_GAME + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_GAME_EQUALS, new String[]{String.valueOf(gameId)},
                null, null, orderByColumn());

        List<Dice> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }
}
