package ch.arkeine.smartgm.model.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.WikiPage;
import ch.arkeine.smartgm.model.dao.persistenceinterface.WikiPagePersistenceInterface;
import ch.arkeine.smartgm.model.database.contracts.WikiTableContract;

/**
 * Data layer access object. Can store a wiki page object in the database.
 * (Provide all CRUD operations)
 */
public class WikiPagePersistence extends ObjectWithIdentifierPersistence<WikiPage>
        implements WikiPagePersistenceInterface {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiPagePersistence(Context context) {
        super(context);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public String getTableName() {
        return WikiTableContract.TABLE_NAME;
    }

    @Override
    public String getColumnId() {
        return WikiTableContract.COLUMN_ID;
    }

    @Override
    public WikiPage createObject(Cursor cursor) {
        WikiPage obj = new WikiPage(cursor.getLong(0), cursor.getLong(1));
        obj.setName(cursor.getString(2));
        obj.setDescription(cursor.getString(3));
        return obj;
    }

    @Override
    public ContentValues createContentValue(WikiPage obj) {
        ContentValues values = new ContentValues();
        values.put(WikiTableContract.COLUMN_NAME, obj.getName());
        values.put(WikiTableContract.COLUMN_UNIVERSE, obj.getUniverseId());
        values.put(WikiTableContract.COLUMN_NAME, obj.getName());
        values.put(WikiTableContract.COLUMN_DESCRIPTION, obj.getDescription());
        return values;
    }

    @Override
    public String getColumnOrderBy() {
        return WikiTableContract.COLUMN_NAME;
    }

    @Override
    public List<WikiPage> listWikiPage(long universeId) {
        final String WHERE_PARENT_EQUALS = WikiTableContract.COLUMN_UNIVERSE + " =?";

        Cursor cursor = db.query(getTableName(), null,
                WHERE_PARENT_EQUALS, new String[]{String.valueOf(universeId)},
                null, null, getColumnOrderBy());

        List<WikiPage> list = createListFromCursor(cursor);

        cursor.close();
        return list;
    }
}
