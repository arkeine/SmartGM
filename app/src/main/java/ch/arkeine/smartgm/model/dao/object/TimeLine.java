package ch.arkeine.smartgm.model.dao.object;

import java.util.Calendar;

/**
 * DAO pattern. Represent a time line store in the database in the object world.
 */
public class TimeLine extends ObjectDescribable {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TimeLine(long id, long gameId) {
        super(id);
        this.gameId = gameId;
        this.date = Calendar.getInstance();
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long gameId;
    private Calendar date;
}
