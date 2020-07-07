package org.example.controller;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.service.AccountService;

import java.io.IOException;
import java.util.Set;

public class AccountController {

    AccountService accountService = new AccountService();

    public boolean createAccount(String name) throws IOException {
        Account account = new Account(name, AccountStatus.ACTIVE);
        return accountService.addUser(account);
    }

    public void deleteAccount(Long id) throws IOException {
        accountService.deleteUser(id);
    }

    public void updateAccount(Account account) throws IOException {
        accountService.updateUser(account);
    }

    public Account findAccount(Long id) throws IOException {
        return accountService.getAccount(id);
    }

    public Set<Account> findAccounts() throws IOException {
        return accountService.getAllAccounts();
    }
}
