package mybank.dao.mybankdeposits;

import mybank.dao.mybankdeposits.entity.Customer;
import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.service.DepositService;
import mybank.dao.mybankdeposits.service.MyBankCustomerService;
import org.apache.catalina.util.CustomObjectInputStream;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class MyBankDepositsApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private DepositService depositService;
    @Mock
    private Logger logger;

    @InjectMocks
    private MyBankCustomerService customerService;

    @Test
    void testFindByUsername() {
        // Create a mock Customer object
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("Nishmitha");
        customer.setCustomerAddress("Bangalore");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(7684758393L);
        customer.setUsername("nishmitha");
        customer.setPassword("123456");
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.singletonList(customer));
        Customer returnedCustomer = customerService.findByUsername("nishmitha");
        assertEquals(customer, returnedCustomer);
    }
    @Test
    void testLoadUserByUsernameNotFound() {
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.emptyList());
        assertThrows(UsernameNotFoundException.class, () -> customerService.loadUserByUsername("nishmitha"));
    }
    @Test
    void testSigningUp() {
        Customer mockCustomer = new Customer(123L,"Nishmitha","Bangalore","active",7847574839L,"nishmitha","123456");
        Customer newCustomer = customerService.signingUp(mockCustomer);
        assertSame(mockCustomer,newCustomer);
    }
    @Test
    void testUpdateAttempts() {
        Customer customer = new Customer();
        customer.setUsername("user");
        customer.setAttempts(5);
        customerService.updateAttempts(customer);
        verify(jdbcTemplate).update(any(String.class), eq(customer.getAttempts()), eq(customer.getUsername()));
    }
    @Test
    void testUpdateStatus() {
        Customer customer = new Customer();
        customer.setUsername("user");
        customerService.updateStatus(customer);
        verify(jdbcTemplate).update(
                eq("update mybank_app_customer set customer_status='inactive' where username=?"),
                eq(customer.getUsername())
        );
    }
    @Test
    void testGetCustomerName() {
        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(Class.class)))
                .thenReturn("Nishmitha");
        String customerName = customerService.getCustomerName("nishmitha");

        assertEquals("Nishmitha", customerName);
    }

    @Test
    void testListByRoi() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        List<DepositsAvailable> mockDepositList = new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(1L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(2L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
        DepositsAvailable deposit2 = new DepositsAvailable();
        deposit2.setDepositId(2L);
        deposit2.setDepositName("Flexi Saver");
        deposit2.setDepositRoi(3.2);
        deposit2.setDepositType("Savings Account");
        deposit2.setDepositDescription("A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());

            // Mock CallableStatement
            CallableStatement callableStatement = mock(CallableStatement.class);
            when(callableStatement.getDouble(1)).thenReturn(4.5);
            when(callableStatement.getObject(2)).thenReturn(resultSet);
            when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(Collections.singletonMap("deposit_data", mockDepositList));

            List<DepositsAvailable> result = depositService.searchDepositsByRoi(4.5);
            assertFalse(result.isEmpty());
            assertEquals(2, result.size());
            assertSame(mockDepositList,result);
            verify(logger).info(anyString());
            assertEquals(deposit1.getDepositType(),result.get(0).getDepositType());
    }

    @Test
    public void testSearchDepositsByRoi_NoDepositsFound() {
        when(jdbcTemplate.call(any(), anyList())).thenReturn(Collections.singletonMap("deposit_data", new ArrayList<>()));
        String expectedErrorMessage = "EXC-001: No deposits available in MyBank";
        assertThrows(DepositException.class, () -> {
            try {
                depositService.searchDepositsByRoi(0.05);
            } catch (DepositException e) {
                assertEquals(expectedErrorMessage, e.getMessage());
                throw e;
            }
        });
    }


    @Test
    void testListAllDeposits() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList = new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(DepositService.DepositsMapper.class))).thenReturn(mockDepositList);

        List<DepositsAvailable> result = depositService.listAllDeposits();

        assertNotEquals(mockDepositList.get(1).getDepositRoi(),result.get(0).getDepositRoi());
        assertFalse(result.isEmpty());
        assertEquals(mockDepositList.get(0).getDepositId(), deposit1.getDepositId());
        assertEquals(mockDepositList.get(0).getDepositName(), deposit1.getDepositName());
        assertNotEquals(mockDepositList.get(1).getDepositDescription(), result.get(0).getDepositDescription());
    }


    @Test
    void listAllFailed() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList = new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(DepositService.DepositsMapper.class))).thenReturn(mockDepositList);

        List<DepositsAvailable> result = depositService.listAllDeposits();

//        assertNull(mockDepositList);
        assertNotEquals(mockDepositList.get(0).getDepositId(), deposit2.getDepositId());
    }


    @Test
    public void testListAllExc() {
        when(jdbcTemplate.query(anyString(), any(DepositService.DepositsMapper.class))).thenReturn(Collections.emptyList());
        assertThrows(DepositException.class, ()->depositService.listAllDeposits());
    }

    @Test
    public void testListAllExcept() {
        when(jdbcTemplate.query(anyString(), any(DepositService.DepositsMapper.class))).thenReturn(Collections.emptyList());

        String expectedErrorMessage = "EXC-001: No deposits available in MyBank";
        assertThrows(DepositException.class, () -> {
            try {
                depositService.listAllDeposits();
            } catch (DepositException e) {
                assertEquals(expectedErrorMessage, e.getMessage());
                throw e;
            }
        });
    }

    @Test
    void testMapRow() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("deposit_id")).thenReturn(123L);
        when(mockResultSet.getString("deposit_name")).thenReturn("Fixed Savings");
        when(mockResultSet.getDouble("deposit_roi")).thenReturn(4.5);
        when(mockResultSet.getString("deposit_type")).thenReturn("Term deposit");
        when(mockResultSet.getString("deposit_description")).thenReturn("A fixed-term savings account");
        DepositService.DepositsMapper depositsMapper = new DepositService.DepositsMapper();
        DepositsAvailable deposit = depositsMapper.mapRow(mockResultSet,1);
        assertEquals(123L, deposit.getDepositId());
        assertNotEquals("Term Deposit", deposit.getDepositName());
    }

    //Customer class methods
    @Test
    void testIsAccountNonExpired() {
        Customer customer = new Customer();
        assertTrue(customer.isAccountNonExpired());
    }
    @Test
    void testIsAccountNonLocked() {
        Customer customer = new Customer();
        assertTrue(customer.isAccountNonLocked(), "Account should be non-locked");
    }
    @Test
    void testIsCredentialsNonExpired() {
        Customer customer = new Customer();
        assertTrue(customer.isCredentialsNonExpired());
    }
    @Test
    void testIsEnabled() {
        Customer customer = new Customer();
        assertTrue(customer.isEnabled(), "User should be enabled");
    }
}
