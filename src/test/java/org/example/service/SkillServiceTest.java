package org.example.service;

import org.example.model.Account;
import org.example.model.Skill;
import org.example.repository.hibernate.HibernateSkillRepoImpl;
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
import static org.mockito.Mockito.when;

public class SkillServiceTest {

    @Mock
    private HibernateSkillRepoImpl repoMock;

    @InjectMocks
    private SkillService serviceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() {
        when(repoMock.findById(1L))
                .thenReturn(new Skill("TestUser"));
        Skill skill = serviceMock.findById(1L);
        assertEquals(skill.getName(), "TestUser");
    }

    @Test
    public void findAll() {
        List<Skill> data = new ArrayList<>();
        data.add(new Skill("TestUser1"));
        data.add(new Skill("TestUser2"));
        given(repoMock.findAll()).willReturn(data);

        List<Skill> allSkills = serviceMock.findAll();
        assertEquals(allSkills.size(), 2);
    }

    @Test
    public void save() {
        given(repoMock.save(any(Skill.class))).willReturn(true);
        boolean result = serviceMock.save(any(Skill.class));
        assertTrue(result);
    }

    @Test
    public void update() {
        when(repoMock.update(any(Skill.class))).thenReturn(true);
        boolean result = serviceMock.update(any(Skill.class));
        assertTrue(result);
    }

    @Test
    public void delete() {
        when(repoMock.delete(1L)).thenReturn(true);
        boolean result = serviceMock.delete(1L);
        assertTrue(result);
    }
}