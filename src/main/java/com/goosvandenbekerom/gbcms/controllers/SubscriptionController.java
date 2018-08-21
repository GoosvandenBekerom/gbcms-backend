package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.Subscription;
import com.goosvandenbekerom.gbcms.exceptions.EntityAlreadyExistsException;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService service;

    @Autowired
    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Subscription> getAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("{id}")
    public Subscription getById(@PathVariable int id) {
        return findById(id);
    }

    @GetMapping("/email/{email}")
    public Subscription getByEmail(@PathVariable String email) {
        return service.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(Subscription.class, email));
    }

    @PostMapping
    public Subscription subscribe(@RequestParam("email") String email) {
        if (service.existsByEmail(email)) throw new EntityAlreadyExistsException(Subscription.class, email);
        return service.save(new Subscription(email));
    }

    @DeleteMapping("{id}")
    public void unsubscribe(@PathVariable int id) {
        Subscription subscription = findById(id);
        service.delete(subscription);
    }

    private Subscription findById(int id) {
        return service.findById(id).orElseThrow(() -> new EntityNotFoundException(Subscription.class, id));
    }
}
