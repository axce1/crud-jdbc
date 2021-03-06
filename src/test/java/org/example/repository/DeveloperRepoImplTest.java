package org.example.repository;

import org.example.model.Account;
import org.example.model.Developer;
import org.example.repository.hibernate.HibernateDeveloperRepoImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeveloperRepoImplTest {
    @Mock
    private HibernateDeveloperRepoImpl developerRepoMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() {
        given(developerRepoMock.save(any(Developer.class))).willReturn(true);
        boolean result = developerRepoMock.save(any(Developer.class));
        assertTrue(result);

    }

    @Test
    public void delete() {
        Mockito.when(developerRepoMock.delete(1L)).thenReturn(true);
        boolean result = developerRepoMock.delete(1L);
        assertTrue(result);
    }

    @Test
    public void findById() {
        when(developerRepoMock.findById(1L))
                .thenReturn(new Developer("TestSkill"));

        Developer developer = developerRepoMock.findById(1L);
        assertEquals("TestSkill", developer.getNickname());

        Mockito.verify(developerRepoMock).findById(1L);
    }

    @Test
    public void update() {
        given(developerRepoMock.update(any(Developer.class))).willReturn(true);
        boolean result = developerRepoMock.update(any(Developer.class));
        assertTrue(result);
    }

    @Test
    public void findAll() {
        List<Developer> data = new ArrayList<>();
        data.add(new Developer("TestUser"));
        given(developerRepoMock.findAll()).willReturn(data);

        List<Developer> allDevelper = developerRepoMock.findAll();
        assertEquals(allDevelper.size(), 1);
        verify(developerRepoMock).findAll();
    }
}