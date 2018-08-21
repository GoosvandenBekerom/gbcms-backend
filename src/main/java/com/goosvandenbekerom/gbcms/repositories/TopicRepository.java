package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    boolean existsByName(String name);
    Optional<Topic> findFirstByName(String name);
}
