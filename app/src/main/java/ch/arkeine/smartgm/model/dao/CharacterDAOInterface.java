package ch.arkeine.smartgm.model.dao;

import java.util.List;

import ch.arkeine.smartgm.model.object.Character;

/**
 * Created by arkeine on 11/9/15.
 */
public interface CharacterDAOInterface extends DAOInterface<Character> {

    List<Character> listCharacter(long gameId);
}
