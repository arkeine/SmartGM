package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.TableItem;

public interface TableItemPersistenceInterface extends PersistenceInterface<TableItem> {

    List<TableItem> listTableItem(long tableId);

    void removeAllTableItem(long tableId);
}
