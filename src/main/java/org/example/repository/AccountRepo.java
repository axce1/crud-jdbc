package org.example.repository;

import org.example.model.Account;

import java.util.Set;

public interface AccountRepo {
    Account getAccount(Long id);
    Set<Account> getAllAccounts();
    boolean addUser(Account account);
    boolean updateUser(Account account);
    boolean deleteUser(Long id);
}
