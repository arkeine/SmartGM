package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.object.WikiPage;
import ch.arkeine.smartgm.model.dao.persistenceinterface.WikiPagePersistenceInterface;
import ch.arkeine.smartgm.view.activity.DataEditionActivity;
import ch.arkeine.smartgm.view.WikiEditionActivity;

/**
 * Presenter for the wiki page edition
 */
public class WikiEditionPresenter  extends DescribableEditionPresenter{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiEditionPresenter(WikiPagePersistenceInterface wikiDAO) {
        super(wikiDAO);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((WikiPage) getObject()).getName();
        String description = ((WikiPage) getObject()).getDescription();

        ((WikiEditionActivity) view).setName(name);
        ((WikiEditionActivity) view).setDescription(description);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new WikiPage(Constants.INVALID_ID, Constants.INVALID_ID);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setUniverseId(long universeId) {
        ((WikiPage) getObject()).setUniverseId(universeId);
    }
}
