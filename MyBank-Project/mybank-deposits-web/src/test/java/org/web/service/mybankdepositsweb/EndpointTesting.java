package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import mybank.dao.mybankdeposits.service.DepositService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.web.service.mybankdepositsweb.configs.DepositSoap;
import services.deposits.ListAllDepositsRequest;
import services.deposits.ListAllDepositsResponse;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;



import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndpointTesting {
    @MockBean
    private DepositInterface depositInterface;
    @InjectMocks
    DepositSoap depositSoap;
    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void testListAll() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList= new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456,"Flexi Saver",3.2,"Savings Account","A flexible savings account");

        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());

//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);

        ListAllDepositsRequest request = new ListAllDepositsRequest();
        ListAllDepositsResponse response = depositSoap.listDeposits(request);

        assertEquals(deposit1.getDepositName(), mockDepositList.get(0).getDepositName());
        assertNotNull(response.getServiceStatus().getStatus());
    }

    @Test
    void testListAllFailed() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList= new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456,"Flexi Saver",3.2,"Savings Account","A flexible savings account");

        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());

        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);

        ListAllDepositsRequest request = new ListAllDepositsRequest();
        ListAllDepositsResponse response = depositSoap.listDeposits(request);

        assertTrue(deposit2.getDepositRoi()==mockDepositList.get(0).getDepositRoi());       //Fail
        assertNull(response.getDeposits());     //Fail

    }
}
