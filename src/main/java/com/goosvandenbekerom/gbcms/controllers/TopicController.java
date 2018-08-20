package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.exceptions.UniqueConstraintException;
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
    public Topic getById(@PathVariable int id)  {
        return findById(id);
    }

    @PostMapping
    public Topic create(@RequestParam("name") String name) {
        try {
            return service.save(new Topic(name, false));
        } catch(Exception e) {
            throw new UniqueConstraintException(Topic.class);
        }
    }

    @PutMapping("{name}")
    public Topic update(@PathVariable int id, @RequestBody String name) {
        Topic topic = findById(id);
        return service.update(topic, name);
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id)  {
        Topic topic = findById(id);
        service.delete(topic);
    }

    private Topic findById(int id)  {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Topic.class, id));
    }
}
