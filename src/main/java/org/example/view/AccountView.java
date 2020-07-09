package org.example.view;

import org.example.controller.AccountController;
import org.example.model.Account;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

public class AccountView {
    AccountController accountController = new AccountController();
    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void createAccount() {
        System.out.println("Please enter new account name: ");
        String input = scanner.nextLine();
        accountController.createAccount(input);
        System.out.println("Created successfully");
    }

    public void deleteAccount() {
        System.out.println("Please enter account ID: ");
        String input = scanner.nextLine();
        accountController.deleteAccount(Long.valueOf(input));
        System.out.println("Deleted successfully");
    }

    public void findAccount() {
        System.out.println("Please enter account ID: ");
        String input = scanner.nextLine();
        accountController.findAccountById(Long.valueOf(input));
        System.out.println("Created successfully");
    }

    public void updateAccount() {
        System.out.println("Please enter account ID: ");
        String id = scanner.nextLine();
        Account account = accountController.findAccountById(Long.valueOf(id));

        System.out.println("Please enter new account name: ");
        String name = scanner.nextLine();
        account.setName(name);
        accountController.updateAccount(account);
        System.out.println("Updated successfully");
    }

    public void findAllAccounts() {
        Set<Account> setAccounts = null;
        System.out.println("Please enter findAll to find all accounts: ");
        String input = scanner.nextLine();
        if(input.equals("findAll"))
            setAccounts = accountController.findAllAccounts();
        System.out.println(setAccounts);
    }
}
