package org.example.repository;

import java.util.Set;

public interface GenericRepository<T, ID> {
    boolean save(T t);

    boolean delete(Long id);

    T findById(Long id);

    boolean update(T t);

    Set<T> findAll();
}
