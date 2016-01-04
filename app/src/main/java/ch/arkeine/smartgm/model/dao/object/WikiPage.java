package ch.arkeine.smartgm.model.dao.object;

/**
 * DAO pattern. Represent a wiki page store in the database in the object world.
 */
public class WikiPage extends ObjectDescribable {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiPage(long id, long universeId) {
        super(id);
        this.universeId = universeId;
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

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long universeId;
}
