package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Comment;
import com.goosvandenbekerom.gbcms.domain.Post;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.CommentService;
import com.goosvandenbekerom.gbcms.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService service;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService service, PostService postService) {
        this.service = service;
        this.postService = postService;
    }

    @GetMapping
    public Iterable<Comment> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("{id}")
    public Comment getById(@PathVariable long id)  {
        return findById(id);
    }

    @PostMapping
    public Comment create(@RequestParam("name") String name, @RequestParam("message") String message, @RequestParam("post") int postId) {
        Post post = postService.findById(postId).orElseThrow(() -> new EntityNotFoundException(Post.class, postId));
        return service.save(new Comment(name, message, post));
    }

    @PutMapping("{id}")
    public Comment update(@PathVariable long id, @RequestParam("name") String name, @RequestParam("message") String message) {
        Comment comment = findById(id);
        return service.update(comment, name, message);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        Comment comment = findById(id);
        service.delete(comment);
    }

    private Comment findById(long id)  {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Comment.class, id));
    }
}
