//package org.web.service.mybankdepositsweb;
//
//import mybank.dao.mybankdeposits.entity.DepositsAvailable;
//import mybank.dao.mybankdeposits.interfaces.DepositInterface;
//import mybank.dao.mybankdeposits.service.DepositService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.web.service.mybankdepositsweb.rest.DepositController;
//import org.web.service.mybankdepositsweb.soap.DepositSoap;
//import services.deposits.ListAllDepositsRequest;
//import services.deposits.ListAllDepositsResponse;
//
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class EndpointTesting {
//    @MockBean
//    private DepositInterface depositInterface;
//    @InjectMocks
//    DepositSoap depositSoap;
//    @InjectMocks
//    DepositController depositController;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(username = "nish", password = "Nish2024")
//    void testDepositsByRoiPass() throws Exception {
//        DepositsAvailable deposit1 = new DepositsAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
//        List<DepositsAvailable> mockDeposits = Stream.of(deposit1).collect(Collectors.toList());
//        when(depositInterface.searchDepositsByRoi(4.5)).thenReturn((ResponseEntity) ResponseEntity.ok(mockDeposits));
//
//        mockMvc.perform(get("/deposit/view/4.5")).
//                andExpect(status().isOk());
//    }
//    @Test
//    @WithMockUser(username = "nish", password = "Nish2024")
//    void testDepositsByRoiFail() throws Exception {
//        DepositsAvailable deposit1 = new DepositsAvailable(123L, "Fixed Savings", 4.5, "Term Deposit", "A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(456L, "Flexi Saver", 3.2, "Savings Account", "A flexible savings account");
//        List<DepositsAvailable> mockDeposits = Stream.of(deposit1).collect(Collectors.toList());
//
//        when(depositInterface.searchDepositsByRoi(4.5)).thenReturn((ResponseEntity) ResponseEntity.ok(mockDeposits));
//
//        mockMvc.perform(get("/deposit/view/4.5")).
//                andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void testListAll() throws SQLSyntaxErrorException {
//        List<DepositsAvailable> mockDepositList= new ArrayList<>();
//        DepositsAvailable deposit1 = new DepositsAvailable(123L,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(456L,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
//
//        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
//
//        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
//
//        ListAllDepositsRequest request = new ListAllDepositsRequest();
//        ListAllDepositsResponse response = depositSoap.listDeposits(request);
//
//        assertEquals(deposit1.getDepositName(), mockDepositList.get(0).getDepositName());
//        assertNotNull(response.getServiceStatus().getStatus());
//    }
//
//    @Test
//    void testListAllFailed() throws SQLSyntaxErrorException {
//        List<DepositsAvailable> mockDepositList= new ArrayList<>();
//        DepositsAvailable deposit1 = new DepositsAvailable(123L,"Fixed Savings",4.5,"Term Deposit","A fixed-term savings account");
//        DepositsAvailable deposit2 = new DepositsAvailable(456L,"Flexi Saver",3.2,"Savings Account","A flexible savings account");
//
//        mockDepositList = Stream.of(deposit1, deposit2).collect(Collectors.toList());
//
//        when(depositInterface.listAllDeposits()).thenReturn(mockDepositList);
//
//        ListAllDepositsRequest request = new ListAllDepositsRequest();
//        ListAllDepositsResponse response = depositSoap.listDeposits(request);
//
//        assertTrue(deposit2.getDepositRoi()==mockDepositList.get(0).getDepositRoi());       //Fail
//        assertNull(response.getDeposits());     //Fail
//
//    }
//}
