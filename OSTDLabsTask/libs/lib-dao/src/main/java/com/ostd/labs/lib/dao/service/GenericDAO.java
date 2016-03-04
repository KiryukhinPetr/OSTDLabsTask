package com.ostd.labs.lib.dao.service;

import com.ostd.labs.lib.dao.interfaces.IGenericDAO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author pkiryukhin
 */
public abstract class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK> {

    private Class entityClass = null;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public void create(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
    }

    @Override
    public void merge(T t) {
        getEntityManager().merge(t);
        getEntityManager().flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(PK id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    @Override
    public void saveOrUpdate(T t) {
        getEntityManager().persist(t);
    }

    @Override
    public void delete(T t) {
        getEntityManager().remove(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        Query query = getEntityManager().createQuery("from " + entityClass.getName());
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findManyResults(String namedQuery, Map<String, Object> parameters) {
        List<T> result = null;
        try {
            Query query = getEntityManager().createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (List<T>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(String namedQuery, Map<String, Object> parameters) {
        int result = 0;
        try {
            Query query = getEntityManager().createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
        try {
            Query query = getEntityManager().createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (T) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    @Override
    abstract public EntityManager getEntityManager();
}