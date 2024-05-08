package org.web.service.mybankdepositsweb;

import mybank.dao.mybankdeposits.entity.Customer;
import mybank.dao.mybankdeposits.entity.DepositsAvailable;
import mybank.dao.mybankdeposits.exception.DepositException;
import mybank.dao.mybankdeposits.interfaces.DepositInterface;
import mybank.dao.mybankdeposits.service.DepositService;
import mybank.dao.mybankdeposits.service.MyBankCustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.web.service.mybankdepositsweb.auth.CustomerFailureHandler;
import org.web.service.mybankdepositsweb.auth.CustomerSuccessHandler;
import org.web.service.mybankdepositsweb.auth.MyBankCustomerAPI;
import org.web.service.mybankdepositsweb.mvc.MvcController;
import org.web.service.mybankdepositsweb.rest.DepositController;
import org.web.service.mybankdepositsweb.soap.DepositSoap;
import services.deposits.ListAllDepositsRequest;
import services.deposits.ListAllDepositsResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EndpointTesting {
    @MockBean
    private DepositInterface depositInterface;
    @InjectMocks
    DepositSoap depositSoap;
    @InjectMocks
    DepositController depositController;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private CustomerSuccessHandler customerSuccessHandler;
    @InjectMocks
    private CustomerFailureHandler customerFailureHandler;
    @Autowired
    MvcController mvcController;
    @Mock
    MyBankCustomerService myBankCustomerService;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private SpringApplicationBuilder mockApplicationBuilder;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MyBankCustomerAPI customerAPI;


    @Test
    @WithMockUser(username = "nish", password = "Nish2024")
    void testDepositsByRoiPass() throws Exception {
        DepositsAvailable deposit1 = new DepositsAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
        List<DepositsAvailable> mockDeposits = Stream.of(deposit1).collect(Collectors.toList());
        when(depositInterface.searchDepositsByRoi(anyDouble())).thenReturn(mockDeposits);

        // Test
        ResponseEntity<?> responseEntity = depositController.getDepositsByRoi("5.0");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockDeposits, responseEntity.getBody());
    }

    @Test
    void testGetDepositsByRoi() throws SQLSyntaxErrorException {
        String strRoi = "4.5";
        Double roi = Double.valueOf(strRoi);
        List<DepositsAvailable> mockDeposits = Collections.singletonList(
                new DepositsAvailable(123L, "Fixed Savings", roi, "Term Deposit", "A fixed-term savings account")
        );
        when(depositInterface.searchDepositsByRoi(roi)).thenReturn(mockDeposits);
        ResponseEntity<?> responseEntity = depositController.getDepositsByRoi(strRoi);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockDeposits, responseEntity.getBody());
        verify(depositInterface, times(1)).searchDepositsByRoi(roi);
    }

    @Test
    void testGetByRoiExc() throws SQLSyntaxErrorException {
        when(depositInterface.searchDepositsByRoi(anyDouble())).thenThrow(new DepositException("No deposits"));

        ResponseEntity<?> responseEntity = depositController.getDepositsByRoi("5.0");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("No deposits", responseEntity.getBody());
    }

    @Test
    void testGetDepositsByRoiSyntaxExc() throws SQLSyntaxErrorException {
        when(depositInterface.searchDepositsByRoi(anyDouble())).thenThrow(new SQLSyntaxErrorException("SQL syntax error"));

        ResponseEntity<?> responseEntity = depositController.getDepositsByRoi("5.0");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Internal Server Error occurred!", responseEntity.getBody());
    }

    @Test
    public void testGetDepositsByRoi_InvalidFormat() {
        String invalidRoi = "abc";
        ResponseEntity<?> responseEntity = depositController.getDepositsByRoi(invalidRoi);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid ROI format. Only numeric values are allowed and ROI cannot be negative", responseEntity.getBody());
    }

    @Test
    public void testGetCustomerName() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("nish");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(myBankCustomerService.getCustomerName(Mockito.anyString())).thenReturn("nish");

        String result = depositController.getCustomerName();
        Assertions.assertEquals("nish", result);
    }

    @Test
    void testListAll() throws SQLSyntaxErrorException {
        List<DepositsAvailable> mockDepositList = new ArrayList<>();
        DepositsAvailable deposit1 = new DepositsAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
        DepositsAvailable deposit2 = new DepositsAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);

        ListAllDepositsRequest request = new ListAllDepositsRequest();
        ListAllDepositsResponse response = depositSoap.listDeposits(request);
        assertEquals(deposit1.getDepositName(), mockDepositList.get(0).getDepositName());
        assertNotNull(response.getServiceStatus().getStatus());
    }

    //Success handler
    @Test
    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerStatus("inactive");

        when(authentication.getPrincipal()).thenReturn(customer);
        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        Mockito.verify(response).encodeRedirectURL("null/login/?errors= contact the admin");
    }

    //MVC Controller test
    @Test
    @WithMockUser(username = "nish")
    void testLogin() {
        String name = mvcController.login();
        assertEquals("index", name);
    }

    @Test
    @WithMockUser(username = "nish")
    void testRedirectLogin() {
        String name = mvcController.redirectLogin();
        assertEquals("index", name);
    }

    @Test
    @WithMockUser(username = "nish")
    void testDashboard() {
        String name = mvcController.dashboard();
        assertEquals("dashboard", name);
    }

    @Test
    @WithMockUser(username = "nish")
    void testView() {
        String name = mvcController.viewDeposits();
        assertEquals("view", name);
    }

    @Test
    @WithMockUser(username = "nish")
    void testError() {
        String name = mvcController.errorPage();
        assertEquals("error", name);
    }

    @Test
    @WithMockUser(username = "nish")
    void testViewByRoi() throws SQLSyntaxErrorException {
        Model model = mock(Model.class);
        String name = mvcController.viewDepositsByRoi(3.2, model);
        assertEquals("view", name);
        assertNotEquals("viewRoi", name);
    }

    //Servlet Initializer
    @Test
    void configureTest() {
        ServletInitializer servletInitializer = new ServletInitializer();
        servletInitializer.configure(mockApplicationBuilder);
        verify(mockApplicationBuilder).sources(MybankDepositsWebApplication.class);
    }

    //CustomerAPI
//    @Test
    void save() {
        Customer mockCustomer = new Customer();
        mockCustomer.setUsername("user");
        mockCustomer.setPassword("user123");
        when(passwordEncoder.encode(mockCustomer.getPassword())).thenReturn("encodedPassword");
        when(myBankCustomerService.signingUp(mockCustomer)).thenReturn(mockCustomer);
        Customer savedCustomer = customerAPI.save(mockCustomer);
        verify(passwordEncoder).encode("user123");
        verify(myBankCustomerService).signingUp(mockCustomer);
        assertEquals("user", savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming getPassword() returns the encoded password
    }

    //Failure Handler
    @Test
    public void testAuthenticationFailureAttempts() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new LockedException("Max Attempts reached account is suspended");

        String username = "testUser";
        request.setParameter("username", username);
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setCustomerStatus("active");
        customer.setAttempts(2); // Maximum attempts are 3
        when(myBankCustomerService.findByUsername(username)).thenReturn(customer);
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/?error=Invalid password. 1 attempts are left.", response.getRedirectedUrl());
    }
    @Test
    public void testAuthenticationFailureSuspend() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new LockedException("Max Attempts reached account is suspended");

        String username = "testUser";
        request.setParameter("username", username);
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setCustomerStatus("active"); // Assuming status allows authentication
        customer.setAttempts(3); // Assuming maximum attempts are 3
        when(myBankCustomerService.findByUsername(username)).thenReturn(customer);

        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/?error=Max Attempts reached! Account is suspended", response.getRedirectedUrl());
    }

}
