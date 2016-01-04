package ch.arkeine.smartgm.model.dao.object;

/**
 * DAO pattern. Represent a generic object with a ID (store in the database) in the object world.
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
        return "ObjectWithIdentifier{" +
                "id=" + id +
                ", lock=" + lock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectWithIdentifier that = (ObjectWithIdentifier) o;

        if (id != that.id) return false;
        return lock == that.lock;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (lock ? 1 : 0);
        return result;
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
