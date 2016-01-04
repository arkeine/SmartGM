package ch.arkeine.smartgm.model.dao.object;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO pattern. Represent a board store in the database in the object world.
 */
public class Board extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Board(long id, long gameId) {
        super(id);
        this.gameId = gameId;
        this.name = "";
        this.listItemOnBoard = new ArrayList<>();
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return name;
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemOnBoard> getListItemOnBoard() {
        return listItemOnBoard;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long gameId;
    private String name;
    private List<ItemOnBoard> listItemOnBoard;

    /* ============================================ */
    // INTERNAL CLASS / INTERFACE
    /* ============================================ */

    public class ItemOnBoard{
        public int posX;
        public int posY;
        public int direction;
        public int size;
        public long itemId;
        public Color fillColor;
    }
}
