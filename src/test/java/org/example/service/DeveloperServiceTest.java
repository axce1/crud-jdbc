package org.example.service;

import org.example.model.Developer;
import org.example.model.Skill;
import org.example.repository.jdbc.JdbcAccountRepoImpl;
import org.example.repository.jdbc.JdbcDeveloperRepoImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class DeveloperServiceTest {

    @Mock
    private JdbcDeveloperRepoImpl repoMock;

    @InjectMocks
    private DeveloperService serviceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findById() {
        when(repoMock.findById(1L))
                .thenReturn(new Developer("TestUser"));
        Developer developer = serviceMock.findById(1L);
        assertEquals(developer.getNickname(), "TestUser");
    }

    @Test
    public void findAll() {
        Set<Developer> data = new HashSet<>();
        data.add(new Developer("TestUser1"));
        data.add(new Developer("TestUser2"));
        given(repoMock.findAll()).willReturn(data);

        Set<Developer> allDevelopers = serviceMock.findAll();
        assertEquals(allDevelopers.size(), 2);
    }

    @Test
    public void save() {
        given(repoMock.save(any(Developer.class))).willReturn(true);
        boolean result = serviceMock.save(any(Developer.class));
        assertTrue(result);
    }

    @Test
    public void update() {
        when(repoMock.update(any(Developer.class))).thenReturn(true);
        boolean result = serviceMock.update(any(Developer.class));
        assertTrue(result);
    }

    @Test
    public void delete() {
        when(repoMock.delete(1L)).thenReturn(true);
        boolean result = serviceMock.delete(1L);
        assertTrue(result);
    }
}