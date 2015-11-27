package ch.arkeine.smartgm.model.dao;

import java.util.List;

import ch.arkeine.smartgm.model.object.Dice;

/**
 * Created by arkeine on 11/9/15.
 */
public interface DiceDAOInterface extends DAOInterface<Dice> {

    List<Dice> listDice(long gameId);
}
