package com.ostd.labs.rest.bean.account;
import com.ostd.labs.dto.account.AccountDTO;
import com.ostd.labs.api.account.AccountService;
import com.ostd.labs.rest.api.account.AccountResource;
import org.apache.cxf.helpers.IOUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Created by pkiryukhin
 */
public class AccountResourceImpl implements AccountResource {


    private static final Logger LOGGER = Logger.getLogger(AccountResourceImpl.class);

    private AccountService accountService;

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<AccountDTO> getAll(){
        return accountService.getAll();
    }

    public AccountDTO getById(Long id){
        return accountService.getById(id);
    }

    public List<AccountDTO> findByBic(String bic){
        return accountService.findByBic(bic);
    }

    public List<AccountDTO> findByIban(String iban){
        return accountService.findByIban(iban);
    }

    public List<AccountDTO> findByIbanAndBic(String iban, String bic){
        return accountService.findByIbanAndBic(iban, bic);
    }

    public void deleteById(Long id){
        accountService.deleteById(id);
    }

    public void update(Long id, String iban, String bic){
        accountService.update(id, iban, bic);
    }

    public void create(String iban, String bic){
        accountService.create(iban, bic);
    }



}
