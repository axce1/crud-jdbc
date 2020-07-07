package org.example.service;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.repository.AccountRepo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AccountServiceTest {

    @Mock
    private AccountRepo accountRepo;
    @InjectMocks
    private AccountService accountService;

    @Test
    public void getAccount() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(accountService.getAccount(1L)).thenReturn(createTestEntity());
        Account account = accountService.getAccount(1L);
        Assert.assertEquals("Test Name", account.getName());
        Assert.assertEquals(AccountStatus.ACTIVE, account.getStatus());
        Mockito.verify(accountService).getAccount(1L);
    }

    @Test
    public void getAllAccounts() {
        Mockito.when(accountService.getAllAccounts().size()).thenReturn((int) 3L);
    }

    @Test
    public void addUser() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(accountService.addUser(createTestEntity())).thenReturn(true);

    }

    @Test
    public void updateUser() {
        MockitoAnnotations.initMocks(this);
        Account account = Mockito.mock(Account.class);
        Mockito.when(accountService.updateUser(account)).thenReturn(true);
    }

    @Test
    public void deleteUser() {
        Mockito.when(accountService.deleteUser(1L)).thenReturn(true);
    }

    private Account createTestEntity() {
        Account myEntity = new Account();
        myEntity.setName("Test Name");
        myEntity.setStatus(AccountStatus.valueOf("ACTIVE"));
        return myEntity;
    }
}