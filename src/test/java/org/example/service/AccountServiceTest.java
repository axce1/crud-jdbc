package org.example.service;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.repository.jdbc.JdbcAccountRepoImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private JdbcAccountRepoImpl repoMock;

    @InjectMocks
    private AccountService serviceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_FindById_Return_Account() {
        when(repoMock.findById(1L))
                .thenReturn(new Account("TestUser", AccountStatus.ACTIVE));
        Account account = serviceMock.findById(1L);
        assertEquals(account.getName(), "TestUser");
    }

    @Test
    public void test_FindAll_Return_SetAccounts() {
        Set<Account> data = new HashSet<>();
        data.add(new Account("TestUser1", AccountStatus.ACTIVE));
        data.add(new Account("TestUser2", AccountStatus.ACTIVE));
        given(repoMock.findAll()).willReturn(data);

        Set<Account> allAccounts = serviceMock.findAll();
        assertEquals(allAccounts.size(), 2);
    }

    @Test
    public void test_Save_Return_True() {
        given(repoMock.save(any(Account.class))).willReturn(true);
        boolean result = serviceMock.save(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void test_Update_Return_True() {
        when(repoMock.update(any(Account.class))).thenReturn(true);
        boolean result = serviceMock.update(any(Account.class));
        assertTrue(result);
    }

    @Test
    public void test_Delete_Return_True() {
        when(repoMock.delete(1L)).thenReturn(true);
        boolean result = serviceMock.delete(1L);
        assertTrue(result);
    }
}