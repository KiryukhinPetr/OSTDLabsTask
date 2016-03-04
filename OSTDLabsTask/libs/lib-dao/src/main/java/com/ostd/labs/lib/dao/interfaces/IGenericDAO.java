package com.ostd.labs.lib.dao.interfaces;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author pkiryukhin
 */
public interface IGenericDAO<T, PK extends Serializable> extends Serializable {

    void create(T t);

    void merge(T t);

    void saveOrUpdate(T t);

    T findById(PK id);

    void delete(T t);

    List<T> findAll();

    List<T> findManyResults(String namedQuery, Map<String, Object> parameters);

    T findOneResult(String namedQuery, Map<String, Object> parameters);

    abstract public EntityManager getEntityManager();
}