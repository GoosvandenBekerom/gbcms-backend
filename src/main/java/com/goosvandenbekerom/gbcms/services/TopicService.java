package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService extends DomainCrudService<Topic, Integer, TopicRepository> {
    public Topic update(Topic topic, String name) {
        if (!name.isEmpty())
            topic.setName(name);
        return repo.save(topic);
    }

    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    public Optional<Topic> findByName(String name) {
        return repo.findFirstByName(name);
    }
}
