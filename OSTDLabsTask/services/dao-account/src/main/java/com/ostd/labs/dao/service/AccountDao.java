package com.ostd.labs.dao.service;

import com.ostd.labs.dao.domain.Account;
import com.ostd.labs.dao.interfaces.IAccountDao;
import com.ostd.labs.lib.dao.service.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pkiryukhin
 */
public class AccountDao extends GenericDAO<Account, Long> implements IAccountDao {
    @PersistenceContext(unitName = "jpaData")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @SuppressWarnings("unused")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Account> findByBic(final String bic){
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("bic", bic);
        return findManyResults(Account.FIND_BY_BIC, parameters);
    }
    public List<Account> findByIban(final String iban){
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("iban", iban);
        return findManyResults(Account.FIND_BY_IBAN, parameters);
    }
    public List<Account> findByIbanAndBic(final String iban, final String bic){
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("iban", iban);
        parameters.put("bic", bic);
        return findManyResults(Account.FIND_BY_IBAN_AND_BIC, parameters);
    }
    public List<Account> getAll(){
        return findAll();
    }
    public void deleteById(Long id){
        delete(findById(id));
    }
}
