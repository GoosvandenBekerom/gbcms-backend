package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.Constants;
import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.exceptions.EntityAlreadyExistsException;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.exceptions.TopicLengthConstraintException;
import com.goosvandenbekerom.gbcms.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService service;

    @Autowired
    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Topic> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("{id}")
    public Topic getById(@PathVariable int id)  {
        return findById(id);
    }

    @PostMapping
    public Topic create(@RequestParam("name") String name) {
        if (name.length() > Constants.MAX_TOPIC_LENGTH) throw new TopicLengthConstraintException();
        if (service.existsByName(name)) throw new EntityAlreadyExistsException(Topic.class, name);
        return service.save(new Topic(name, false));
    }

    @PutMapping("{id}")
    public Topic update(@PathVariable int id, @RequestParam("name") String name) {
        if (name.length() > Constants.MAX_TOPIC_LENGTH) throw new TopicLengthConstraintException();
        if (service.existsByName(name)) throw new EntityAlreadyExistsException(Topic.class, name);
        Topic topic = findById(id);
        return service.update(topic, name);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id)  {
        Topic topic = findById(id);
        service.delete(topic);
    }

    private Topic findById(int id)  {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Topic.class, id));
    }
}
