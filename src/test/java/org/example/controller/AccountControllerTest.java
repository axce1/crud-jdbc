package org.example.controller;

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

import java.sql.Statement;
import java.sql.Connection;

import static org.junit.Assert.*;

public class AccountControllerTest {

    @InjectMocks
    private ConnectionFactory dbConnection;

    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;

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
    public void createAccount() {
    }

    @Test
    public void deleteAccount() {
    }

    @Test
    public void updateAccount() {
    }

    @Test
    public void findAccount() {
    }

    @Test
    public void findAccounts() {
    }
}