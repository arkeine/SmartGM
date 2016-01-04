package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.TimeLine;

public interface TimeLinePersistenceInterface  extends PersistenceInterface<TimeLine>{

    List<TimeLine> listTimeLine(long gameId);
}
