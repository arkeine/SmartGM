package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Game;

public interface GamePersistenceInterface extends PersistenceInterface<Game> {

    List<Game> listGame(long universeId);
}
