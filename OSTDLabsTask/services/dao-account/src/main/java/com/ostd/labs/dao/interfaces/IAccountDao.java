package com.ostd.labs.dao.interfaces;

import com.ostd.labs.dao.domain.Account;
import com.ostd.labs.lib.dao.interfaces.IGenericDAO;
import java.util.List;

/**
 * @author pkiryukhin
 */
public interface IAccountDao extends IGenericDAO<Account, Long> {

    List<Account> findByBic(String bic);
    List<Account> findByIban(String iban);
    List<Account> findByIbanAndBic(String iban, String bic);
    List<Account> getAll();
    void deleteById(Long id);
    void deleteAll();

}
