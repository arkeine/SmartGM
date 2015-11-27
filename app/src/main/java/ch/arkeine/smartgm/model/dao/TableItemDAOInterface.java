package ch.arkeine.smartgm.model.dao;

import java.util.List;

import ch.arkeine.smartgm.model.object.TableItem;

/**
 * Created by arkeine on 11/9/15.
 */
public interface TableItemDAOInterface extends DAOInterface<TableItem> {

    List<TableItem> listTableItem(long tableId);

    void removeAllTableId(long tableId);
}
