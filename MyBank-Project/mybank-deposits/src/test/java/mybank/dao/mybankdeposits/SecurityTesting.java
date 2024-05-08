package mybank.dao.mybankdeposits;

import mybank.dao.mybankdeposits.entity.Customer;
import mybank.dao.mybankdeposits.service.DepositService;
import mybank.dao.mybankdeposits.service.MyBankCustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SecurityTesting {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private MyBankCustomerService customerService;

    @Test
    void testFindByUsername() {
        // Create a mock Customer object
        Customer customer = new Customer();
        customer.setUsername("nishmitha");
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(Collections.singletonList(customer));
        Customer returnedCustomer = customerService.findByUsername("nishmitha");
        assertEquals(customer, returnedCustomer);
    }

}
