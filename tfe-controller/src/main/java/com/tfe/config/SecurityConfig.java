package com.tfe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    //    private Auth0UserIdFilter auth0UserIdFilter;
//    public SecurityConfig(Auth0UserIdFilter auth0UserIdFilter){
//        this.auth0UserIdFilter = auth0UserIdFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/parent/me").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .addFilterBefore(auth0UserIdFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2ResourceServer(res -> res
//                        .jwt(jwt -> jwt
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
//                        )
//                );
//
//        return http.build();
//    }
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        String issuerUri = "https://dev-ox066ujh.us.auth0.com/";
//        return NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
//    }
//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        return new JwtAuthenticationConverter();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/stagedesc/**").permitAll()
                        .requestMatchers("/api/stageinst/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("admin")
                        .requestMatchers("/api/parent/**").hasRole("parent")
                        .requestMatchers("/api/super/**").hasAnyRole("parent", "admin")
                        .requestMatchers("/api/secured").authenticated()
                        .requestMatchers("/api/me").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                );

        return http.build();
    }
}