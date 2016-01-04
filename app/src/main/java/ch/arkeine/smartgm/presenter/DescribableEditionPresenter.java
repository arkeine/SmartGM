package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.model.dao.object.ObjectDescribable;
import ch.arkeine.smartgm.model.dao.persistenceinterface.PersistenceInterface;


/**
 * Generic presenter to manipulate generic object with description and name
 */
public abstract class DescribableEditionPresenter extends DataEditionPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DescribableEditionPresenter(PersistenceInterface dao) {
        super(dao);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setName(String s) {
        ((ObjectDescribable) getObject()).setName(s);
    }

    public String getName() {
        return ((ObjectDescribable) getObject()).getName();
    }

    public void setDescription(String s) {
        ((ObjectDescribable) getObject()).setDescription(s);
    }

    public String getDescription() {
        return ((ObjectDescribable) getObject()).getDescription();
    }
}
