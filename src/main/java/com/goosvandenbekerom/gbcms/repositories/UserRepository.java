package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findFirstByUsername(String username);
}
