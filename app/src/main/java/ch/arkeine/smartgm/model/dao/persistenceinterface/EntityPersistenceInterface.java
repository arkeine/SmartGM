package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Entity;

public interface EntityPersistenceInterface extends PersistenceInterface<Entity> {

    List<Entity> listItemByType(long universeId, Entity.Type type);
}
