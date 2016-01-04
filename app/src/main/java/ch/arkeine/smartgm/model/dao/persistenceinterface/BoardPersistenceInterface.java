package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Board;

public interface BoardPersistenceInterface extends PersistenceInterface<Board> {

    List<Board> listBoard(long gameId);
}
