package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.exceptions.TopicConstraintException;
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

    @GetMapping("{id}")
    public Topic getById(@PathVariable int id)  {
        return findById(id);
    }

    @PostMapping
    public Topic create(@RequestParam("name") String name) {
        try {
            return service.save(new Topic(name, false));
        } catch (EntityNotFoundException enf) {
            throw enf;
        } catch(Exception e) {
            throw new TopicConstraintException();
        }
    }

    @PutMapping("{id}")
    public Topic update(@PathVariable int id, @RequestParam("name") String name) {
        try {
            Topic topic = findById(id);
            return service.update(topic, name);
        } catch (EntityNotFoundException enf) {
            throw enf;
        } catch(Exception e) {
            throw new TopicConstraintException();
        }
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
