package ch.arkeine.smartgm.model.dao;

import java.util.List;

/**
 * Created by arkeine on 11/9/15.
 */
public interface DAOInterface<T> {

    /* ============================================ */
    // CRUD
    /* ============================================ */

    long[] listId();

    T find(long id);

    T create(T obj);

    T update(T obj);

    void delete(T obj);

    List<T> listAll();
}
