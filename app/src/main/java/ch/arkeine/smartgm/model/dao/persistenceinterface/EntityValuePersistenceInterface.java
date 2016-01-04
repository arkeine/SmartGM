package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.EntityValue;
import ch.arkeine.smartgm.model.dao.object.TableItem;

public interface EntityValuePersistenceInterface extends PersistenceInterface<EntityValue> {

    List<EntityValue> listEntityValue(long entityId);

    void removeAllEntityValue(long entityId);
}
