package com.ostd.labs.lib.dao.interfaces;

import java.io.Serializable;

/**
 * Created by pkiryukhin
 */
public interface IDtoConverterDao {
    <T, PK extends Serializable> T findById(Class<T> entityClass, PK id);
    void removeEntity(Object entityToRemove);

    void persist(Object entity);
}
