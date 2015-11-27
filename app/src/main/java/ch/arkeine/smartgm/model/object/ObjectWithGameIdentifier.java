package ch.arkeine.smartgm.model.object;

/**
 * Created by arkeine on 11/24/15.
 */
public class ObjectWithGameIdentifier extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ObjectWithGameIdentifier(long id, long gameId) {
        super(id);
        this.gameId = gameId;
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

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long gameId;
}
