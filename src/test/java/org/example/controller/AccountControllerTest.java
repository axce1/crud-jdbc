package org.example.controller;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    private AccountService serviceMock;

    @InjectMocks
    private AccountController controllerMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_createAccount() {
        when(serviceMock.findById(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));
        Account account = controllerMock.findAccountById(1L);
        assertEquals(account.getName(), "TestUser");
    }

    @Test
    public void test_deleteAccount() {
        when(serviceMock.delete(anyLong())).thenReturn(true);
        boolean result = controllerMock.deleteAccount(anyLong());
        assertTrue(result);
    }

    @Test
    public void test_updateAccount() {
        when(serviceMock.update(any(Account.class))).thenReturn(true);
        boolean result = controllerMock.updateAccount(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void test_findAccountById() {
        when(serviceMock.findById(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));
        Account account = controllerMock.findAccountById(1L);
        assertEquals(account.getName(), "TestUser");
    }

    @Test
    public void test_findAllAccounts() {
        List<Account> data = new ArrayList<>();
        data.add(new Account("TestUser1", AccountStatus.ACTIVE));
        data.add(new Account("TestUser2", AccountStatus.ACTIVE));
        given(serviceMock.findAll()).willReturn(data);

        List<Account> allAccounts = controllerMock.findAllAccounts();
        assertEquals(allAccounts.size(), 2);
    }
}