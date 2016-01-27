package ch.arkeine.smartgm.presenter;


import java.util.Calendar;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.object.TimeLine;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TimeLinePersistenceInterface;
import ch.arkeine.smartgm.view.TimeLineEditionActivity;
import ch.arkeine.smartgm.view.activity.edition.DataEditionActivity;

/**
 * Presenter for the time line edition
 */
public class TimeLineEditionPresenter extends DescribableEditionPresenter{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TimeLineEditionPresenter(TimeLinePersistenceInterface daoTimeLine) {
        super(daoTimeLine);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((TimeLine) getObject()).getName();
        Calendar date = ((TimeLine) getObject()).getDate();

        ((TimeLineEditionActivity) view).setName(name);
        ((TimeLineEditionActivity) view).setDate(date);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new TimeLine(Constants.INVALID_ID, Constants.INVALID_ID);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setGameId(long gameId) {
        ((TimeLine) getObject()).setGameId(gameId);
    }

    public void setDate(Calendar c) {
        ((TimeLine) getObject()).setDate(c);
        updateView();
    }

    public Calendar getDate() {
        return ((TimeLine) getObject()).getDate();
    }

}
