package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Post;
import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.PostService;
import com.goosvandenbekerom.gbcms.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService service;
    private final TopicService topicService;

    @Autowired
    public PostController(PostService service, TopicService topicService) {
        this.service = service;
        this.topicService = topicService;
    }

    @GetMapping
    public Iterable<Post> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable int id) {
        return findById(id);
    }

    @PostMapping
    public Post create(@RequestBody String title, @RequestBody String summary, @RequestBody String content, @RequestBody String topicName) {
        Topic topic = findTopicById(topicName);
        return service.save(new Post(title, summary, content, topic));
    }

    @PutMapping("{id}")
    public Post update(@PathVariable int id, @RequestBody String title, @RequestBody String summary, @RequestBody String content, @RequestBody String topicName) {
        Post post = findById(id);
        Topic topic = topicService.findById(topicName).orElse(post.getTopic());
        return service.update(post, title, summary, content, topic);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        Post post = findById(id);
        service.delete(post);
    }

    private Post findById(int id) {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Post.class, id));
    }

    private Topic findTopicById(String id) {
        return topicService.findById(id).orElseThrow(() -> new EntityNotFoundException(Topic.class, id));
    }
}
