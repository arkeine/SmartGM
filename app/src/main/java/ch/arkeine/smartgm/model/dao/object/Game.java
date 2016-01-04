package ch.arkeine.smartgm.model.dao.object;

/**
 * DAO pattern. Represent a game store in the database in the object world.
 */
public class Game extends ObjectDescribable {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Game(long id, long universeId) {
        super(id);
        this.universeId = universeId;
        this.note = "";
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getUniverseId() {
        return universeId;
    }

    public void setUniverseId(long universeId) {
        this.universeId = universeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long universeId;
    private String note;
}
