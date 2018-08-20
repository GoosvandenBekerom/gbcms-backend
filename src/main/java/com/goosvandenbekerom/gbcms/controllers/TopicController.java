package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<Topic> getAll() {
        return service.findAll();
    }

    @GetMapping("{name}")
    public Topic getById(@PathVariable String name)  {
        return findById(name);
    }

    @PostMapping
    public Topic create(@RequestBody String name) {
        return service.save(new Topic(name, false));
    }

    @PutMapping("{name}")
    public Topic update(@PathVariable String name, @RequestBody String newName) {
        Topic topic = findById(name);
        return service.update(topic, newName);
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name)  {
        Topic topic = findById(name);
        service.delete(topic);
    }

    private Topic findById(String id)  {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Topic.class, id));
    }
}
