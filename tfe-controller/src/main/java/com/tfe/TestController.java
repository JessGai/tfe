package com.tfe;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello public!";
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello admin!";
    }

    @PreAuthorize("hasRole('parent')")
    @GetMapping("/parent/hello")
    public String parentHello() {
        return "Hello parent!";
    }
    @GetMapping("/secured")
    public String secured() {
        return "Token JWT valide !";
    }
    @PreAuthorize("hasAnyRole('parent', 'admin')")
    @GetMapping("/super/hello")
    public String superUserHello() {
        return "Hello parent!";
    }

    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken jwt) {
            return Map.of(
                    "subject", jwt.getToken().getSubject(),
                    "email", jwt.getToken().getClaimAsString("email"),
                    "authorities", jwt.getAuthorities(),
                    "allClaims", jwt.getToken().getClaims()
            );
        }
        return Map.of("error", "Not authenticated");
    }
}
