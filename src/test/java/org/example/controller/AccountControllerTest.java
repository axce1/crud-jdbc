package org.example.controller;

import org.example.model.Account;
import org.example.model.AccountStatus;
import org.example.service.AccountService;
import org.example.utils.ConnectionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class AccountControllerTest {

    @InjectMocks
    private ConnectionFactory dbConnection;

    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;

    @Mock
    private AccountController acMock;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockDBConnection() throws Exception {
        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
        int value = dbConnection.executeQuery("");
        Assert.assertEquals(value, 0);
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
    }

    @Test
    public void createAccount() throws IOException {
        Mockito.when(acMock.createAccount("super1mock")).thenReturn(true);
    }

    @Test
    public void deleteAccount() {
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void findAccount() throws IOException {
//        Mockito.when(acMock.createAccount("super1mock")).thenReturn(true);
        Mockito.verify(acMock).findAccount(Mockito.anyLong());
    }

    @Test
    public void findAccounts() throws IOException {
        Set<Account> data = new HashSet<>();
        data.add(new Account("wer2", AccountStatus.ACTIVE));
        Mockito.when(acMock.findAccounts()).thenReturn(data);
    }
}