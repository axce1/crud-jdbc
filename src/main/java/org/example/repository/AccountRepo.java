package org.example.repository;

import org.example.model.Account;

import java.util.Set;

public interface AccountRepo {
    Account findById(Long id);
    Set<Account> findAll();
    boolean save(Account account);
    boolean update(Account account);
    boolean delete(Long id);
}
