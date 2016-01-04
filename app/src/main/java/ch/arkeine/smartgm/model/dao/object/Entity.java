package ch.arkeine.smartgm.model.dao.object;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO pattern. Represent a item store in the database in the object world.
 */
public class Entity extends ObjectDescribable {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Entity(long id, long universeId, Long gameId) {
        super(id);
        this.universeId = universeId;
        this.gameId = gameId;
        this.type = Type.NOT_DEF;
        this.values = new ArrayList<>();
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getUniverseId() {
        return universeId;
    }

    public void setUniverseId(long universeId) {
        this.universeId = universeId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<EntityValue> getListValues() {
        return values;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long universeId;
    private Long gameId;    // can be null if only universe
    private Long parentId;  // can be null if no parent
    private Type type;
    private List<EntityValue> values;

    /* ============================================ */
    // CONSTANT
    /* ============================================ */

    public enum Type{NOT_DEF, PLAYER, CHARACTER, STOCKED, EQUIPPED, MODEL}
}
