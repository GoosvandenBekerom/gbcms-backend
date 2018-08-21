package com.goosvandenbekerom.gbcms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

abstract public class DomainCrudService<T, TID, TREPO extends PagingAndSortingRepository<T, TID>> {

    @Autowired
    TREPO repo;

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

    public Iterable<T> findAll(Pageable pageable) {
        return repo.findAll(pageable);
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
