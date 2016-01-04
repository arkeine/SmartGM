package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.object.Universe;
import ch.arkeine.smartgm.model.dao.persistenceinterface.UniversePersistenceInterface;
import ch.arkeine.smartgm.view.DataEditionActivity;
import ch.arkeine.smartgm.view.UniverseEditionActivity;


/**
 * Presenter for the universe edition
 */
public class UniverseEditionPresenter extends DescribableEditionPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public UniverseEditionPresenter(UniversePersistenceInterface dao) {
        super(dao);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((Universe) getObject()).getName();
        ((UniverseEditionActivity) view).setName(name);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Universe(Constants.INVALID_ID);
    }

}
