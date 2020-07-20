package org.example.repository.hibernate;

import org.example.model.Account;
import org.example.repository.AccountRepo;

import java.util.Set;

public class HibernateAccountRepoImpl implements AccountRepo {
    @Override
    public boolean save(Account account) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public Set<Account> findAll() {
        return null;
    }
}
