package com.ostd.labs.bean.account;
import com.ostd.labs.api.account.AccountService;
import com.ostd.labs.dto.account.AccountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author pkiryukhin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/META-INF/spring/bean-account-spring.xml"
})
@Ignore
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    private <T> T loadJsonFile(String fileName, Class<T> resultClass) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/cea2datamart/" + fileName)) {
            return mapper.readValue(inputStream, resultClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void importTableDataToDataMartTest() {
        String templateName = "unittest";
        String reportName = "unittest_importTableDataToDataMartTest";

//        SummaryReportDto dto = new SummaryReportDto();
//        dto.setTemplateName(templateName);
//        dto.setReportName(reportName);
//        TableDataDTO tableDataDTO = loadJsonFile("1-TableDataDTO.js", TableDataDTO.class);
//        ReportPeriodDto period = new ReportPeriodDto(PeriodType.CURRENT_MONTH_TO_DATE);
//        databaseImportService.importTableDataToDataMart(dto, tableDataDTO, period);

        //TODO: create test DAO to view datamart and check results
    }




}
