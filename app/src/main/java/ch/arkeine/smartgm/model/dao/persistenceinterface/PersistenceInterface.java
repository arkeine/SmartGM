package ch.arkeine.smartgm.model.dao.persistenceinterface;

/**
 * Abstraction of which methods the data layer access objects should provide
 */
public interface PersistenceInterface<T> {

    /* ============================================ */
    // CRUD
    /* ============================================ */

    long[][] listId();

    T find(long... id);

    T create(T obj);

    T update(T obj);

    void delete(T obj);
}
