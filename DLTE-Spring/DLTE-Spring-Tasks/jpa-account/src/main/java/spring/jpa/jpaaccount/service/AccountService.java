package spring.jpa.jpaaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.jpaaccount.model.Account;
import spring.jpa.jpaaccount.remote.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account callSave(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> callFindAll() {
        return (List<Account>) accountRepository.findAll();
    }
}
