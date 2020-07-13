package org.example.repository.jdbc;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.repository.jdbc.JdbcAccountRepoImpl;
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
import static org.mockito.Mockito.when;

public class JdbcAccountRepoImplTest {

    @Mock
    private JdbcAccountRepoImpl accountRepoMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_FindById_Return_Account() {
        when(accountRepoMock.findById(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));

        Account account = accountRepoMock.findById(1L);
        assertEquals("TestUser", account.getName());
        assertEquals(AccountStatus.ACTIVE, account.getStatus());

        Mockito.verify(accountRepoMock).findById(1L);
    }

    @Test
    public void test_FindById_Return_Null() {
        when(accountRepoMock.findById(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));
        Account account = accountRepoMock.findById(2L);
        assertNull(account);
    }

    @Test
    public void test_FindById_Throw_Exception() {
        when(accountRepoMock.findById(1L))
                .thenThrow(Exception.class);
        accountRepoMock.findById(1L);

        Mockito.verify(accountRepoMock).findById(1L);
    }

    @Test
    public void test_FindAll_Return_True() {
        Set<Account> data = new HashSet<>();
        data.add(new Account("TestUser", AccountStatus.ACTIVE));
        given(accountRepoMock.findAll()).willReturn(data);

        Set<Account> allAccounts = accountRepoMock.findAll();
        assertEquals(allAccounts.size(), 1);
        verify(accountRepoMock).findAll();
    }

    @Test
    public void test_FindAll_Contain() {
        Set<Account> data = new HashSet<>();
        Account account = new Account("TestUser", AccountStatus.ACTIVE);
        data.add(account);
        given(accountRepoMock.findAll()).willReturn(data);

        Set<Account> allAccounts = accountRepoMock.findAll();
        assertTrue(allAccounts.contains(account));
    }

    @Test
    public void test_FindAll_Not_Contain() {
        Set<Account> data = new HashSet<>();
        Account account = new Account("TestUser", AccountStatus.ACTIVE);
        data.add(account);
        given(accountRepoMock.findAll()).willReturn(data);

        Set<Account> allAccounts = accountRepoMock.findAll();
        assertFalse(allAccounts.contains(new Account("NewTestUser", AccountStatus.ACTIVE)));
    }

    @Test
    public void test_FindAll_Return_False() {
        Set<Account> data = new HashSet<>();
        data.add(new Account("TestUser", AccountStatus.ACTIVE));
        given(accountRepoMock.findAll()).willReturn(data);

        Set<Account> allAccounts = accountRepoMock.findAll();
        assertNotEquals(allAccounts.size(), 2);
    }

    @Test(expected = Exception.class)
    public void test_FindAll_Throw_Exception() {
        given(accountRepoMock.findAll()).willThrow(Exception.class);
        accountRepoMock.findAll();
    }

    @Test
    public void test_Save_Return_True() {
        given(accountRepoMock.save(any(Account.class))).willReturn(true);
        boolean result = accountRepoMock.save(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void test_Save_Return_False() {
        given(accountRepoMock.save(any(Account.class))).willReturn(false);
        boolean result = accountRepoMock.save(any(Account.class));
        assertFalse(result);
    }

    @Test(expected = Exception.class)
    public void test_Save_Throw_Exception() {
        given(accountRepoMock.save(any(Account.class))).willThrow(Exception.class);
        accountRepoMock.save(any(Account.class));
    }

    @Test
    public void test_Update_Return_True() {
        Mockito.when(accountRepoMock.update(any(Account.class))).thenReturn(true);
        boolean result = accountRepoMock.update(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void test_Update_Return_False() {
        Mockito.when(accountRepoMock.update(any(Account.class))).thenReturn(false);
        boolean result = accountRepoMock.update(any(Account.class));
        assertFalse(result);
    }

    @Test
    public void test_Delete_Return_True() {
        Mockito.when(accountRepoMock.delete(1L)).thenReturn(true);
        boolean result = accountRepoMock.delete(1L);
        assertTrue(result);
    }

    @Test
    public void test_Delete_Return_False() {
        Mockito.when(accountRepoMock.delete(1L)).thenReturn(false);
        boolean result = accountRepoMock.delete(1L);
        assertFalse(result);
    }
}