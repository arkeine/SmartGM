package ch.arkeine.smartgm.model.object;

/**
 * Created by Arkeine on 08.11.2015.
 */
public class Game extends ObjectWithIdentifier {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public Game(long id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }

    public Game(long id, String title) {
        this(id, title, "");
    }

    public Game(long id) {
        this(id, "");
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private String title;
    private String description;
}
