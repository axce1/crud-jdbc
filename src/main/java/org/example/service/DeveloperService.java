package org.example.service;

import org.example.model.Developer;
import org.example.repository.hibernate.HibernateDeveloperRepoImpl;

import java.util.List;

public class DeveloperService {
    
    private HibernateDeveloperRepoImpl developerRepo = new HibernateDeveloperRepoImpl();

    public Developer findById(Long id) {
        return developerRepo.findById(id);
    }

    public List<Developer> findAll() {
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
