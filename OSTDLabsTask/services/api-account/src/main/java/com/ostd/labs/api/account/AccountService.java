package com.ostd.labs.api.account;
import com.ostd.labs.dto.account.AccountDTO;
import java.util.List;

/**
 * @author pkiryukhin
 */
public interface AccountService {
    List<AccountDTO> getAll();

    AccountDTO getById(Long id);

    List<AccountDTO> findByBic(String bic);

    List<AccountDTO> findByIban(String iban);

    List<AccountDTO> findByIbanAndBic(String iban, String bic);

    void deleteById(Long id);

    void deleteAll();

    void update(Long id, String iban, String bic);

    void create(String iban, String bic);

    void createAccount(String iban, String bic);

}
