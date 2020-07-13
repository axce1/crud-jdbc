package org.example.service;

import org.example.model.Developer;
import org.example.repository.jdbc.JdbcDeveloperRepoImpl;

import java.util.Set;

public class DeveloperService {
    
    private  JdbcDeveloperRepoImpl developerRepo;

    public Developer findById(Long id) {
        return developerRepo.findById(id);
    }

    public Set<Developer> findAll() {
        return developerRepo.findAll();
    }

    public Boolean save(Developer developer) {
        return developerRepo.save(developer);
    }

    public Boolean update(Developer developer) {
        return developerRepo.update(developer);
    }

    public Boolean delete(Long id) {
        return developerRepo.delete(id);
    }
}
