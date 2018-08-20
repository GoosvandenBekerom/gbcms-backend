package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.repositories.TopicRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicService extends DomainCrudService<Topic, String, TopicRepository> {
    public Topic update(Topic topic, String name) {
        if (!name.isEmpty())
            topic.setName(name);
        return topic;
    }
}
