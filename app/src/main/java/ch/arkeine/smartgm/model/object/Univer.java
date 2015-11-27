package ch.arkeine.smartgm.model.object;

/**
 * Created by arkeine on 11/26/15.
 */
public class Univer extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Univer(long id) {
        super(id);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private String name;
}
