package ch.arkeine.smartgm.model.dao.object;

import java.util.HashMap;
import java.util.Map;

/**
 * DAO pattern. Represent a generic object with name and description
 * (which is store in the database) in the object world.
 */
public class ObjectDescribable extends ObjectWithIdentifier{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ObjectDescribable(long id) {
        super(id);
        this.name = "";
        this.description = "";
    }

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    public String toString() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private String name;
    private String description;
}
