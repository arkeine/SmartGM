package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Dice;
import ch.arkeine.smartgm.model.dao.persistenceinterface.DicePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.DiceTableContract;

/**
 * Data layer access object. Can store a dice object in the database.
 * (Provide all CRUD operations)
 */
public class DicePersistence extends ObjectWithIdentifierPersistence<Dice>
        implements DicePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DicePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return DiceTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DiceTableContract.COLUMN_ID;
    }

    @Override
    public Dice createObject(Cursor cursor) {
        return new Dice(cursor.getLong(0), cursor.getLong(1), cursor.getInt(2));
    }

    @Override
    public ContentValues createContentValue(Dice obj) {
        ContentValues values = new ContentValues();
        values.put(DiceTableContract.COLUMN_NBFACE, obj.getNbFaces());
        values.put(DiceTableContract.COLUMN_UNIVERSE, obj.getUniverseId());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return DiceTableContract.COLUMN_NBFACE;
    }

    @Override
    public List<Dice> listDice(long universeId) {
        final String WHERE_UNIVERSE_EQUALS = DiceTableContract.COLUMN_UNIVERSE + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_UNIVERSE_EQUALS, new String[]{String.valueOf(universeId)},
                null, null, getColumnOrderBy());

        List<Dice> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }
}
