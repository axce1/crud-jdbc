package org.example.service;

import org.example.model.Skill;
import org.example.repository.hibernate.HibernateSkillRepoImpl;

import java.util.Set;

public class SkillService {
    
    private HibernateSkillRepoImpl skillRepo;

    public Skill findById(Long id) {
        return skillRepo.findById(id);
    }

    public Set<Skill> findAll() {
        return skillRepo.findAll();
    }

    public Boolean save(Skill skill) {
        return skillRepo.save(skill);
    }

    public Boolean update(Skill skill) {
        return skillRepo.update(skill);
    }

    public Boolean delete(Long id) {
        return skillRepo.delete(id);
    }
}
