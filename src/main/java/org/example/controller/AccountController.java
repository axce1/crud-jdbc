package org.example.controller;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.service.AccountService;

import java.util.List;

public class AccountController {

    AccountService accountService = new AccountService();

    public Boolean createAccount(String name) {
        Account account = new Account(name, AccountStatus.ACTIVE);
        return accountService.save(account);
    }

    public Boolean deleteAccount(Long id) {
        return accountService.delete(id);
    }

    public Boolean updateAccount(Account account) {
        return accountService.update(account);
    }

    public Account findAccountById(Long id) {
        return accountService.findById(id);
    }

    public List<Account> findAllAccounts() {
        return accountService.findAll();
    }
}
