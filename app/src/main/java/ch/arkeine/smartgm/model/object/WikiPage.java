package ch.arkeine.smartgm.model.object;

/**
 * Created by arkeine on 11/26/15.
 */
public class WikiPage extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiPage(long id) {
        super(id);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public Univer getUniver() {
        return univer;
    }

    public void setUniver(Univer univer) {
        this.univer = univer;
    }

    public String getFullQualifiedName() {
        return fullQualifiedName;
    }

    public void setFullQualifiedName(String fullQualifiedName) {
        this.fullQualifiedName = fullQualifiedName;
    }

/* ============================================ */
    // FIELD
    /* ============================================ */

    private Univer univer;
    private String fullQualifiedName;

}
