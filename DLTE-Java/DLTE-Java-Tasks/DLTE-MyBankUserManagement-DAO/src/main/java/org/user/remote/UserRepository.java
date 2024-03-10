package org.user.remote;

import org.user.entity.Account;

public interface UserRepository {
    void createAccount(Account account);
    Account authenticate(String username);
}
