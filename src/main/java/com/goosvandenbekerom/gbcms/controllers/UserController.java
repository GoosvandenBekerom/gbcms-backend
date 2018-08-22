package com.goosvandenbekerom.gbcms.controllers;

import com.goosvandenbekerom.gbcms.domain.User;
import com.goosvandenbekerom.gbcms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User register(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("firstname") String firstname,
                         @RequestParam("lastname") String lastname)
    {
        return service.register(username, password, firstname, lastname);
    }
}
