package spring.explore.springbootjdbc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UsersMyBankAPI {
    @Autowired
    UsersMyBankService service;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public UsersMyBank save(@RequestBody UsersMyBank usersMyBank) {
        usersMyBank.setPassword(passwordEncoder.encode(usersMyBank.getPassword()));
        return service.signingUp(usersMyBank);
    }

}
