package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Integer> {
    boolean existsByName(String name);

    Optional<Topic> findFirstByName(String name);
}
