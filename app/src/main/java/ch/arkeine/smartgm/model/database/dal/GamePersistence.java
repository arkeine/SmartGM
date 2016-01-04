package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Game;
import ch.arkeine.smartgm.model.dao.persistenceinterface.GamePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.GameTableContract;

/**
 * Data layer access object. Can store a game object in the database.
 * (Provide all CRUD operations)
 */
public class GamePersistence extends ObjectWithIdentifierPersistence<Game>
        implements GamePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public GamePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return GameTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return GameTableContract.COLUMN_ID;
    }

    @Override
    public Game createObject(Cursor cursor) {
        Game obj = new Game(cursor.getLong(0), cursor.getLong(1));
        obj.setName(cursor.getString(2));
        obj.setDescription(cursor.getString(3));
        obj.setNote(cursor.getString(4));

        return obj;
    }

    @Override
    public ContentValues createContentValue(Game obj) {
        ContentValues values = new ContentValues();
        values.put(GameTableContract.COLUMN_NAME, obj.getName());
        values.put(GameTableContract.COLUMN_DESCRIPTION, obj.getDescription());
        values.put(GameTableContract.COLUMN_UNIVERSE, obj.getUniverseId());
        values.put(GameTableContract.COLUMN_PLAYERS, obj.getNote());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return GameTableContract.COLUMN_NAME;
    }

    @Override
    public List<Game> listGame(long universeId) {
        final String WHERE_UNIVERSE_EQUALS = GameTableContract.COLUMN_UNIVERSE + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_UNIVERSE_EQUALS, new String[]{String.valueOf(universeId)},
                null, null, getColumnOrderBy());

        List<Game> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }
}
