package com.goosvandenbekerom.gbcms.security;

import com.goosvandenbekerom.gbcms.domain.User;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Primary
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImplementation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(User.class, username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        return Collections.emptyList();
    }
}
