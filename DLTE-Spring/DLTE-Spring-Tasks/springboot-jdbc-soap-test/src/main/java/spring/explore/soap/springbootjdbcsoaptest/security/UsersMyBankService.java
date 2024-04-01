package spring.explore.soap.springbootjdbcsoaptest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersMyBankService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public UsersMyBank signingUp(UsersMyBank usersMyBank) {
        int ack = jdbcTemplate.update("insert into users_mybank values(?,?,?,?,?,?,?)",new Object[]{
                usersMyBank.getName(), usersMyBank.getUsername(),
                usersMyBank.getPassword(), usersMyBank.getEmail(),
                usersMyBank.getContact(), usersMyBank.getAadhaar(),
                usersMyBank.getRole()
        });
        return usersMyBank;
    }

    public UsersMyBank findByUsername(String username) {
        UsersMyBank usersMyBank = jdbcTemplate.queryForObject("select * from users_mybank where username=?",
                new Object[]{username}, new BeanPropertyRowMapper<>(UsersMyBank.class));
        return usersMyBank;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersMyBank users = findByUsername(username);
        if (users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
}
