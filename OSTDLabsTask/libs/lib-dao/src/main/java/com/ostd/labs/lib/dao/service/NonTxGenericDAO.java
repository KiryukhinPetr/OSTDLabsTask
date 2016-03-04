package com.ostd.labs.lib.dao.service;

import com.ostd.labs.lib.dao.interfaces.IDtoConverterDao;
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
public abstract class NonTxGenericDAO<T, PK extends Serializable> {

    private Class<T> entityClass = null;

    @SuppressWarnings("unchecked")
    public NonTxGenericDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public T newEntity() {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public void create(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
    }

    @SuppressWarnings("unchecked")
    public T findById(PK id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    public void saveOrUpdate(T t) {
        getEntityManager().persist(t);
    }

    public void delete(T t) {
        getEntityManager().remove(t);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Query query = getEntityManager().createQuery("from " + entityClass.getName());
        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
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

    abstract public EntityManager getEntityManager();
}