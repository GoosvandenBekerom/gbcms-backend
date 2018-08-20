package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.repositories.TopicRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicService extends DomainCrudService<Topic, Integer, TopicRepository> {}
