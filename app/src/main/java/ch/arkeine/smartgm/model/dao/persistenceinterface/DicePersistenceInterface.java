package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Dice;

public interface DicePersistenceInterface extends PersistenceInterface<Dice> {

    List<Dice> listDice(long universeId);
}
