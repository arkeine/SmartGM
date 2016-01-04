package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Table;

public interface TablePersistenceInterface extends PersistenceInterface<Table> {

    List<Table> listTable(long universeId);
}
