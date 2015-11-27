package ch.arkeine.smartgm.model.database.daoimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.CharacterDAOInterface;
import ch.arkeine.smartgm.model.database.tablecontract.DataBaseCharacterTable;
import ch.arkeine.smartgm.model.object.Character;

/**
 * Created by arkeine on 11/9/15.
 */
public class CharacterDAO extends DataBaseDAO<Character> implements CharacterDAOInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public CharacterDAO(Context context) {
        super(context);
    }

    /* ============================================ */
    // CRUD
    /* ============================================ */

    @Override
    public String getTableName() {
        return DataBaseCharacterTable.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return DataBaseCharacterTable.COLUMN_ID;
    }

    @Override
    public Character createObject(Cursor cursor) {
        return new Character(cursor.getLong(0), cursor.getLong(1), cursor.getString(2));
    }

    @Override
    public ContentValues getContentValue(Character obj) {
        ContentValues values = new ContentValues();
        values.put(DataBaseCharacterTable.COLUMN_NAME, obj.getName());
        values.put(DataBaseCharacterTable.COLUMN_GAME, obj.getGameId());
        return values;
    }

    @Override
    public String orderByColumn() {
        return DataBaseCharacterTable.COLUMN_NAME;
    }

    @Override
    public List<Character> listCharacter(long gameId) {
        final String WHERE_GAME_EQUALS = DataBaseCharacterTable.COLUMN_GAME + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_GAME_EQUALS, new String[]{String.valueOf(gameId)},
                null, null, orderByColumn());

        List<Character> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }

    @Override
    public Character saveRelatedData(Character obj) {
        return obj;
    }

    @Override
    public Character loadRelatedData(Character obj) {
        return obj;
    }
}
