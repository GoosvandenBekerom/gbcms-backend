package com.goosvandenbekerom.gbcms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

abstract public class DomainCrudService<T, TID, TREPO extends CrudRepository<T, TID>> {

    @Autowired TREPO repo;

    public <S extends T> S save(S entity) {
        return repo.save(entity);
    }

    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    public Optional<T> findById(TID id) {
        return repo.findById(id);
    }

    public boolean existsById(TID id) {
        return repo.existsById(id);
    }

    public Iterable<T> findAll() {
        return repo.findAll();
    }

    public long count() {
        return repo.count();
    }

    public void deleteById(TID id) {
        repo.deleteById(id);
    }

    public void delete(T entity) {
        repo.delete(entity);
    }
}
