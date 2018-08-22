package com.goosvandenbekerom.gbcms.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.goosvandenbekerom.gbcms.Constants;
import com.goosvandenbekerom.gbcms.domain.User;
import com.goosvandenbekerom.gbcms.exceptions.EntityNotFoundException;
import com.goosvandenbekerom.gbcms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class JwtService implements AuthenticationSuccessHandler {
    private static final Algorithm algorithm = Algorithm.HMAC256(Constants.JWT_SECRET);
    private static final String PREFIX = "Bearer ";

    private final UserService userService;

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(User.class, username));

        String token = JWT.create().withSubject(username).withClaim("userId", user.getId()).sign(algorithm);
        response.addHeader(HttpHeaders.AUTHORIZATION, PREFIX + token);
    }
}
