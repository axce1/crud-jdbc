package org.example.service;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.repository.AccountRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class AccountServiceTest {

    @Mock
    private AccountRepo accountRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAccount_Return_Account() {
        Mockito.when(accountRepo.getAccount(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));

        Account account = accountRepo.getAccount(1L);
        assertEquals("TestUser", account.getName());
        assertEquals(AccountStatus.ACTIVE, account.getStatus());

        Mockito.verify(accountRepo).getAccount(1L);
    }

    @Test
    public void getAccount_Return_Null() {
        Mockito.when(accountRepo.getAccount(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));
        Account account = accountRepo.getAccount(2L);
        assertNull(account);

        Mockito.verify(accountRepo).getAccount(2L);
    }

    @Test(expected = Exception.class)
    public void getAccount_Throw_Exception() {
        Mockito.when(accountRepo.getAccount(1L))
                .thenThrow(Exception.class);
        accountRepo.getAccount(1L);

        Mockito.verify(accountRepo).getAccount(1L);
    }


    @Test
    public void getAllAccounts_Return_True() {
        Set<Account> data = new HashSet<>();
        data.add(new Account("TestUser", AccountStatus.ACTIVE));
        given(accountRepo.getAllAccounts()).willReturn(data);

        Set<Account> allAccounts = accountRepo.getAllAccounts();
        assertEquals(allAccounts.size(), 1);
        verify(accountRepo).getAllAccounts();
    }

    @Test
    public void getAllAccounts_Contain() {
        Set<Account> data = new HashSet<>();
        Account account = new Account("TestUser", AccountStatus.ACTIVE);
        data.add(account);
        given(accountRepo.getAllAccounts()).willReturn(data);

        Set<Account> allAccounts = accountRepo.getAllAccounts();
        assertTrue(allAccounts.contains(account));
    }

    @Test
    public void getAllAccounts_Not_Contain() {
        Set<Account> data = new HashSet<>();
        Account account = new Account("TestUser", AccountStatus.ACTIVE);
        data.add(account);
        given(accountRepo.getAllAccounts()).willReturn(data);

        Set<Account> allAccounts = accountRepo.getAllAccounts();
        assertFalse(allAccounts.contains(new Account("NewTestUser", AccountStatus.ACTIVE)));
    }

    @Test
    public void getAllAccounts_Return_False() {
        Set<Account> data = new HashSet<>();
        data.add(new Account("TestUser", AccountStatus.ACTIVE));
        given(accountRepo.getAllAccounts()).willReturn(data);

        Set<Account> allAccounts = accountRepo.getAllAccounts();
        assertNotEquals(allAccounts.size(), 2);
    }

    @Test(expected = Exception.class)
    public void getAllAccounts_Throw_Exception() {
        given(accountRepo.getAllAccounts()).willThrow(Exception.class);
        accountRepo.getAllAccounts();
    }

    @Test
    public void addUser_Return_True() {
        given(accountRepo.addUser(any(Account.class))).willReturn(true);
        boolean result = accountRepo.addUser(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void addUser_Return_False() {
        given(accountRepo.addUser(any(Account.class))).willReturn(false);
        boolean result = accountRepo.addUser(any(Account.class));
        assertFalse(result);
    }

    @Test(expected = Exception.class)
    public void addUser_Throw_Exception() {
        given(accountRepo.addUser(any(Account.class))).willThrow(Exception.class);
        accountRepo.addUser(any(Account.class));
    }

    @Test
    public void updateUser_Return_True() {
        Mockito.when(accountRepo.updateUser(any(Account.class))).thenReturn(true);
        boolean result = accountRepo.updateUser(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void updateUser_Return_False() {
        Mockito.when(accountRepo.updateUser(any(Account.class))).thenReturn(false);
        boolean result = accountRepo.updateUser(any(Account.class));
        assertFalse(result);
    }

    @Test
    public void deleteUser_Return_True() {
        Mockito.when(accountRepo.deleteUser(1L)).thenReturn(true);
        boolean result = accountRepo.deleteUser(1L);
        assertTrue(result);
    }

    @Test
    public void deleteUser_Return_False() {
        Mockito.when(accountRepo.deleteUser(1L)).thenReturn(false);
        boolean result = accountRepo.deleteUser(1L);
        assertFalse(result);
    }

}