package ch.arkeine.smartgm.model.dao.persistenceinterface;

import java.util.List;

import ch.arkeine.smartgm.model.dao.object.WikiPage;

public interface WikiPagePersistenceInterface extends PersistenceInterface<WikiPage>{

    List<WikiPage> listWikiPage(long universeId);
}
