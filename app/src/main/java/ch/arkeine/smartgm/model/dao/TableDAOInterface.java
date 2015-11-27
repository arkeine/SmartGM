package ch.arkeine.smartgm.model.dao;

import java.util.List;

import ch.arkeine.smartgm.model.object.Table;

/**
 * Created by arkeine on 11/9/15.
 */
public interface TableDAOInterface extends DAOInterface<Table> {

    List<Table> listTable(long gameId);
}
