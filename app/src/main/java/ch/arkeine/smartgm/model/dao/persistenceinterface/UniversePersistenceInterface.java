package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.Universe;

public interface UniversePersistenceInterface extends PersistenceInterface<Universe> {

    List<Universe> listAll();
}
