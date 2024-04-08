package mybank.dao.mybankdeposits;

import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.service.DepositService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
class MyBankDepositApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DepositService depositService;

    @Test
    void testListAllDeposits() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList= new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);

        List<DepositsAvailable> result =depositService.listAllDeposits();


        assertEquals(mockDepositList.get(0).getDepositId(),deposit1.getDepositId());
        assertEquals(mockDepositList.get(0).getDepositName(), deposit1.getDepositName());
        assertNotNull(mockDepositList);
    }

    @Test
    void listAllFailed() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList= new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);

        List<DepositsAvailable> result =depositService.listAllDeposits();

        assertNotNull(mockDepositList);
        assertEquals(mockDepositList.get(0).getDepositId(), deposit2.getDepositId());
    }


    @Test
    public void testListAllExc() {
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.emptyList());

//        List<DepositsAvailable> mockDepositList= new ArrayList<>();
//        DepositsAvailable deposit1 = new DepositsAvailable(123,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(456,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
//        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(mockDepositList);

        assertThrows(DepositException.class, ()->depositService.listAllDeposits());

        }

}
