package org.example.repository.hibernate;

import org.example.model.Developer;
import org.example.repository.DeveloperRepo;

import java.util.Set;

public class HibernateDeveloperRepoImpl implements DeveloperRepo {
    @Override
    public boolean save(Developer developer) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Developer findById(Long id) {
        return null;
    }

    @Override
    public boolean update(Developer developer) {
        return false;
    }

    @Override
    public Set<Developer> findAll() {
        return null;
    }
}
