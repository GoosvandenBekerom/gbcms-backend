package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.User;
import com.goosvandenbekerom.gbcms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends DomainCrudService<User, Integer, UserRepository> {

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User register(String username, String password, String firstname, String lastname) {
        return repo.save(new User(username, encoder.encode(password), firstname, lastname));
    }

    public Optional<User> findByUsername(String username) {
        return repo.findFirstByUsername(username);
    }
}
