package ch.arkeine.smartgm.model.dao;

import java.util.List;

import ch.arkeine.smartgm.model.object.Statistic;

/**
 * Created by arkeine on 11/9/15.
 */
public interface StatisticDAOInterface extends DAOInterface<Statistic> {

    List<Statistic> listStatistic(long characterId);

    void removeAllCharacterId(long characterId);
}
