package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.model.dao.DAOInterface;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.view.DataEditionActivity;

/**
 * Created by arkeine on 11/26/15.
 */
public class WikiActivityPresenter extends DataEditionActivityPresenter{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiActivityPresenter(DAOInterface dao) {
        super(dao);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {

    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return null;
    }
}
