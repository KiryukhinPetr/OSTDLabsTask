package com.ostd.labs.bean.account;
import com.ostd.labs.api.account.AccountService;
import com.ostd.labs.dto.account.AccountDTO;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;


/**
 * @author pkiryukhin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/bean-account-spring.xml"
})

//@Ignore
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Before
    public void setUpToAccountTestData(){
        accountService.createAccount("1", "1");
        accountService.createAccount("1", "2");
        accountService.createAccount("1", "3");
        accountService.createAccount("2", "4");
    }

    @Test
    public void findByIban(){
        List<AccountDTO> accountDTOList = accountService.findByIban("2");
        Assert.assertTrue("", accountDTOList.size() == 1 && accountDTOList.get(0).getIban().equals("2"));
    }

    @Test
    public void findByBic(){
        List<AccountDTO> accountDTOList = accountService.findByBic("1");
        Assert.assertTrue("", accountDTOList.size() == 1 && accountDTOList.get(0).getBic().equals("1"));
    }

    @Test
    public void findByIbanAndBic(){
        List<AccountDTO> accountDTOList = accountService.findByIbanAndBic("1", "1");
        Assert.assertTrue("", accountDTOList.size() == 1 && accountDTOList.get(0).getIban().equals("1") && accountDTOList.get(0).getBic().equals("1"));
    }


    @Test
    public void deleteById(){
        accountService.createAccount("10", "10");
        Long id = accountService.findByIbanAndBic("10", "10").get(0).getId();
        accountService.deleteById(id);
        List<AccountDTO> accountDTOList = accountService.findByIbanAndBic("10", "10");
        Assert.assertTrue("", accountDTOList.size() == 0);
    }
    @Test
    public void update(){
        accountService.createAccount("11", "11");
        Long id = accountService.findByIbanAndBic("11", "11").get(0).getId();
        accountService.update(id, "20", "20");
        AccountDTO accountDTO = accountService.getById(id);
        Assert.assertTrue("", accountDTO.getId() == id && accountDTO.getIban().equals("20") && accountDTO.getBic().equals("20"));
    }
    @After
    public void tearDownToAccountTestData(){
        accountService.deleteAll();
    }




}
