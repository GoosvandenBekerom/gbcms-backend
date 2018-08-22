package com.goosvandenbekerom.gbcms.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.goosvandenbekerom.gbcms.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserDetailsService userDetailsService;

    JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || header.isEmpty()) {
            super.doFilterInternal(request, response, chain);
            return;
        }

        String token = header.replace(Constants.AUTH_HEADER_PREFIX, "");

        if (token.isEmpty())
            throw new BadCredentialsException("The received authorization token was empty");

        try {
            DecodedJWT decoded = JWT.decode(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(decoded.getSubject());
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities()));
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new BadCredentialsException("Failed to decode authorization token");
        }
        chain.doFilter(request, response);
    }
}
