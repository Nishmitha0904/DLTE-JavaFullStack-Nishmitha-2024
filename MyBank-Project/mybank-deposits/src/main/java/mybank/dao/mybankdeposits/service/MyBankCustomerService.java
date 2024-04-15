package mybank.dao.mybankdeposits.service;

import mybank.dao.mybankdeposits.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankCustomerService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(MyBankCustomerService.class);

    public Customer signingUp(Customer customer) {
        int ack = jdbcTemplate.update("insert into mybank_app_customer(customer_id,customer_name,customer_address,customer_status,customer_contact,username,password) values(?,?,?,?,?,?,?)",
                new Object[]{
                        customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(),
                        customer.getCustomerStatus(), customer.getCustomerContact(), customer.getUsername(), customer.getPassword()
                });
        return customer;
    }

    public Customer findByUsername(String username) {
        Customer customer = jdbcTemplate.queryForObject("select * from mybank_app_customer where username=?",
                new Object[]{username}, new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }

    public void updateAttempts(Customer customer) {
        jdbcTemplate.update("update mybank_app_customer set attempts=? where username=?",
                new Object[]{customer.getAttempts(), customer.getUsername()});
    }

    public void updateStatus(Customer customer) {
        jdbcTemplate.update("update mybank_app_customer set customer_status='inactive' where username=?",
                new Object[]{customer.getUsername()});
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByUsername(username);
        if (customer == null)
            throw new UsernameNotFoundException(username);
        return customer;
    }
}
