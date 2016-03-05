package com.ostd.labs.bean.account;
import com.ostd.labs.jms.bean.account.JmsMessageProducer;
import com.ostd.labs.dao.convert.AccountDtoConverter;
import com.ostd.labs.dao.domain.Account;
import com.ostd.labs.dao.interfaces.IAccountDao;
import com.ostd.labs.dto.account.AccountDTO;
import com.ostd.labs.api.account.AccountService;
import com.ostd.labs.lib.dao.interfaces.IDtoConverterDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pkiryukhin
 */
@Transactional("transactionManager")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);
    private static final SimpleDateFormat PG_DATE_FORMAT = new SimpleDateFormat("MM-dd-YYYY"); //ISO, DMY

    private IAccountDao accountDao;
    private IDtoConverterDao dtoConverterDao;
    private JmsMessageProducer jmsMessageProducer;

    public IAccountDao getAccountDao() {
        return accountDao;
    }

    @Required
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }
    public IDtoConverterDao getDtoConverterDao() {
        return dtoConverterDao;
    }

    @Required
    public void setDtoConverterDao(IDtoConverterDao dtoConverterDao) {
        this.dtoConverterDao = dtoConverterDao;
    }

    public JmsMessageProducer getJmsMessageProducer() {
        return jmsMessageProducer;
    }

    @Required
    public void setJmsMessageProducer(JmsMessageProducer jmsMessageProducer) {
        this.jmsMessageProducer = jmsMessageProducer;
    }


    public List<AccountDTO> getAll(){
        List<Account> accountList = accountDao.getAll();
        return AccountDtoConverter.INSTANCE.convertAccountListToDto(accountList);
    }

    public AccountDTO getById(Long id){
        Account account = accountDao.findById(id);
        return AccountDtoConverter.INSTANCE.toDto(account);
    }

    public List<AccountDTO> findByBic(String bic){
        List<Account> accountList = accountDao.findByBic(bic);
        return AccountDtoConverter.INSTANCE.convertAccountListToDto(accountList);
    }

    public List<AccountDTO> findByIban(String iban){
        List<Account> accountList = accountDao.findByIban(iban);
        return AccountDtoConverter.INSTANCE.convertAccountListToDto(accountList);
    }

    public List<AccountDTO> findByIbanAndBic(String iban, String bic){
        List<Account> accountList = accountDao.findByIbanAndBic(iban, bic);
        return AccountDtoConverter.INSTANCE.convertAccountListToDto(accountList);
    }

    public void deleteById(Long id){
        accountDao.deleteById(id);
    }

    public void deleteAll(){
        accountDao.deleteAll();
    }

    public void update(Long id, String iban, String bic){
        Account account = accountDao.findById(id);
        account.setIban(iban);
        account.setBic(bic);
        accountDao.saveOrUpdate(account);
    }

    public void create(String iban, String bic){
        Account account = new Account();
        account.setIban(iban);
        account.setBic(bic);
        accountDao.create(account);
        jmsMessageProducer.send("New Account with iban: " + iban + " and bic: " + bic + " was created" );
    }

    public void createAccount(String iban, String bic){
        Account account = new Account();
        account.setIban(iban);
        account.setBic(bic);
        accountDao.create(account);

    }

}
