package com.tfe.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.io.IOException;
import jakarta.servlet.Filter;
@Component
public class Auth0UserIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String auth0UserId = jwt.getSubject(); // "sub" claim
            System.out.println("Token JWT détecté, subject = " + auth0UserId);
            request.setAttribute("auth0UserId", auth0UserId);
        } else {
            System.out.println("Aucun token JWT valide détecté dans le contexte de sécurité.");
        }

        chain.doFilter(request, response);
    }
}
