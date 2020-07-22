package org.example.repository;

import org.example.model.Account;
import org.example.model.Skill;
import org.example.repository.hibernate.HibernateSkillRepoImpl;
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

public class SkillRepoImplTest {
    @Mock
    private HibernateSkillRepoImpl skillRepoMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() {
        given(skillRepoMock.save(any(Skill.class))).willReturn(true);
        boolean result = skillRepoMock.save(any(Skill.class));
        assertTrue(result);

    }

    @Test
    public void delete() {
        Mockito.when(skillRepoMock.delete(1L)).thenReturn(true);
        boolean result = skillRepoMock.delete(1L);
        assertTrue(result);
    }

    @Test
    public void findById() {
        when(skillRepoMock.findById(1L))
                .thenReturn(new Skill("TestSkill"));

        Skill skill = skillRepoMock.findById(1L);
        assertEquals("TestSkill", skill.getName());

        Mockito.verify(skillRepoMock).findById(1L);
    }

    @Test
    public void update() {
        given(skillRepoMock.update(any(Skill.class))).willReturn(true);
        boolean result = skillRepoMock.update(any(Skill.class));
        assertTrue(result);
    }

    @Test
    public void findAll() {
        List<Skill> data = new ArrayList<>();
        data.add(new Skill("TestSkill"));
        given(skillRepoMock.findAll()).willReturn(data);

        List<Skill> allSkills = skillRepoMock.findAll();
        assertEquals(allSkills.size(), 1);
        verify(skillRepoMock).findAll();
    }
}