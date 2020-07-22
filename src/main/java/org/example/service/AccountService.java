package org.example.service;

import org.example.model.Account;
import org.example.repository.hibernate.HibernateAccountRepoImpl;

import java.util.List;
import java.util.Set;

public class AccountService {

    private HibernateAccountRepoImpl accountRepo = new HibernateAccountRepoImpl();

    public Account findById(Long id) {
        return accountRepo.findById(id);
    }

    public List<Account> findAll() {
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
