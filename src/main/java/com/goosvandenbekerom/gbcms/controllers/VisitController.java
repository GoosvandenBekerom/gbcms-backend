package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Post;
import com.goosvandenbekerom.gbcms.domain.Visit;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.PostService;
import com.goosvandenbekerom.gbcms.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private final VisitService service;
    private final PostService postService;

    @Autowired
    public VisitController(VisitService service, PostService postService) {
        this.service = service;
        this.postService = postService;
    }

    @GetMapping
    public Iterable<Visit> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Visit getById(@PathVariable long id)  {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Visit.class, id));
    }

    @PostMapping
    public Visit create(@RequestParam("ip") String ip, @RequestParam("post") int postId) {
        Post post = postService.findById(postId).orElseThrow(() -> new EntityNotFoundException(Post.class, postId));
        return service.save(new Visit(ip, post));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }
}
