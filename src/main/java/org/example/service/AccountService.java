package org.example.service;

import org.example.model.hibernate.Account;
import org.example.repository.hibernate.HibernateAccountRepoImpl;

import java.util.Set;

public class AccountService {

    private HibernateAccountRepoImpl accountRepo;

    public Account findById(Long id) {
        return accountRepo.findById(id);
    }

    public Set<Account> findAll() {
        return accountRepo.findAll();
    }

    public Boolean save(Account account) {
        return accountRepo.save(account);
    }

    public Boolean update(Account account) {
        return accountRepo.update(account);
    }

    public Boolean delete(Long id) {
        return accountRepo.delete(id);
    }
}
