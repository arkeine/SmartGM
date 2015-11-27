package ch.arkeine.smartgm.model.object;

/**
 * Created by Arkeine on 12.11.2015.
 */
public class ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ObjectWithIdentifier(long id) {
        this.id = id;
        this.lock = true;
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ObjectWithIdentifier (id = ");
        sb.append(id);
        sb.append(")");
        return sb.toString();
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (lock) throw new IllegalStateException("Identifier is lock");
        this.id = id;
    }

    public boolean isIdentifierLock() {
        return lock;
    }

    public void lockIdentifier() {
        this.lock = true;
    }

    public void unlockIdentifier() {
        this.lock = false;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private long id;
    private boolean lock;
}
