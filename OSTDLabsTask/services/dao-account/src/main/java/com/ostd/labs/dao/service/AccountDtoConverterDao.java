package com.ostd.labs.dao.service;

import com.ostd.labs.lib.dao.interfaces.IDtoConverterDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by pkiryukhin
 */
public class AccountDtoConverterDao implements IDtoConverterDao {
    @PersistenceContext(unitName = "jpaData")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @SuppressWarnings("unused")
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T, PK extends Serializable> T findById(final Class<T> entityClass, final PK id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void removeEntity(final Object entityToRemove) {
        entityManager.remove(entityToRemove);
    }

    @Override
    public void persist(final Object entity) {
        entityManager.persist(entity);
    }

}
