package com.tfe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Routes publiques
                        .requestMatchers(HttpMethod.POST, "/api/parent").permitAll()
                        .requestMatchers("/api/stagedesc/**", "/api/stageinst/**", "/api/enfant/**", "/api/public/**", "/api/panier/**", "/api/parent/**", "/swagger-ui/**", "/doc/**", "/v3/api-docs/**").permitAll()

                        // Authentification simple requise
                        .requestMatchers(HttpMethod.GET, "/api/parent/exists").authenticated()
                        .requestMatchers("/api/parent/me").authenticated()
                        .requestMatchers("/api/secured", "/api/me").authenticated()
                        .requestMatchers("/create-checkout-session").authenticated()
                        .requestMatchers("/api/paiement/create-checkout-session").authenticated()
                        .requestMatchers("/api/paiement/**").authenticated()
                        .requestMatchers("/api/historique/**").authenticated()
                        .requestMatchers("/api/parent/historique/**").authenticated()

                        // Rôles
                        .requestMatchers("/api/parent/**").hasRole("parent")
                        .requestMatchers("/api/admin/**").hasRole("admin")
                        .requestMatchers("/api/super/**").hasAnyRole("parent", "admin")
                        .requestMatchers("/api/transactions/**", "/api/transactions/open/**").hasRole("parent")
                        .requestMatchers("/api/inscription/**").hasRole("parent")
                        // Tout le reste est interdit sauf si public explicite
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                )
                .securityContext(context -> context.requireExplicitSave(false))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}