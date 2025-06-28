package com.tfe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.tfe.config.Auth0UserIdFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private Auth0UserIdFilter auth0UserIdFilter;
    public SecurityConfig(Auth0UserIdFilter auth0UserIdFilter){
        this.auth0UserIdFilter = auth0UserIdFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/parent/me").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(auth0UserIdFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2ResourceServer(res -> res
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );

        return http.build();
    }
    @Bean
    public JwtDecoder jwtDecoder() {
        String issuerUri = "https://dev-ox066ujh.us.auth0.com/";
        return NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
    }
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }
}