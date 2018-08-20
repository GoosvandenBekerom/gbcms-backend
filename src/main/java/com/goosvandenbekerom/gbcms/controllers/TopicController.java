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

    @GetMapping
    @RequestMapping("{id}")
    public Topic getById(@PathVariable int id) throws EntityNotFoundException {
        return findById(id);
    }

    @PostMapping
    public Topic create(@RequestBody String name) {
        return service.save(new Topic(name, false));
    }

    @PutMapping
    @RequestMapping("{id}")
    public Topic update(@PathVariable int id, @RequestBody String name) throws EntityNotFoundException {
        Topic topic = findById(id);
        return service.update(topic, name);
    }

    @DeleteMapping
    @RequestMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) throws EntityNotFoundException {
        Topic topic = findById(id);
        service.delete(topic);
    }

    private Topic findById(int id) throws EntityNotFoundException {
        return service.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
