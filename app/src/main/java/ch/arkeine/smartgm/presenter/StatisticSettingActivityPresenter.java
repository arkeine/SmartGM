package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.DAOInterface;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.object.Statistic;
import ch.arkeine.smartgm.view.DataEditionActivity;
import ch.arkeine.smartgm.view.DiceSettingActivity;
import ch.arkeine.smartgm.view.StatisticSettingActivity;

/**
 * Created by arkeine on 11/26/15.
 */
public class StatisticSettingActivityPresenter extends DataEditionActivityPresenter{

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public StatisticSettingActivityPresenter(DAOInterface dao) {
        super(dao);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        Statistic statistic = (Statistic) getObject();
        StatisticSettingActivity convertView = (StatisticSettingActivity) view;

        convertView.setName(statistic.getName());
        convertView.setMax(statistic.getMax());
        convertView.setMin(statistic.getMin());
        convertView.setCurrent(statistic.getCurrent());
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Statistic(Constants.invalidId, Constants.invalidId);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setCharacterId(long characterId) {
        ((Statistic) getObject()).setCharacterId(characterId);
    }

    public void setName(String name) {
        ((Statistic) getObject()).setName(name);
    }

    public void setMin(int value) {
        ((Statistic) getObject()).setMin(value);
    }

    public void setMax(int value) {
        ((Statistic) getObject()).setMax(value);
    }

    public void setCurrent(int value) {
        ((Statistic) getObject()).setCurrent(value);
    }
}
