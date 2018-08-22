package com.goosvandenbekerom.gbcms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authManager, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.setAuthenticationManager(authManager);
        if (authenticationSuccessHandler != null)
            this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    }

    @Data
    private class UsernamePassword {
        private String username;
        private String password;
    }

    @Override
    public String obtainUsername(HttpServletRequest http) {
        try {
            UsernamePassword data = new ObjectMapper().readValue(http.getInputStream(), UsernamePassword.class);
            return data.username;
        } catch (Exception e) {
            return super.obtainUsername(http);
        }
    }

    @Override
    public String obtainPassword(HttpServletRequest http) {
        try {
            UsernamePassword data = new ObjectMapper().readValue(http.getInputStream(), UsernamePassword.class);
            return data.password;
        } catch (Exception e) {
            return super.obtainPassword(http);
        }
    }
}
