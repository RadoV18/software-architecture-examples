package com.radov18.borrowing.security.config;

import com.radov18.borrowing.security.jwt.KeycloakJwtTokenConverter;
import com.radov18.borrowing.security.jwt.TokenConverterProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    private final KeycloakJwtTokenConverter keycloakJwtTokenConverter;

    public HttpSecurityConfig(TokenConverterProperties properties) {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter
                = new JwtGrantedAuthoritiesConverter();
        this.keycloakJwtTokenConverter
                = new KeycloakJwtTokenConverter(
                jwtGrantedAuthoritiesConverter,
                properties);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakJwtTokenConverter)))

                .authorizeHttpRequests(authorizeHttpRequests -> {
                    // Allow unauthenticated access to all endpoints
                    authorizeHttpRequests
                            .anyRequest().permitAll();
                });

        return http.build();
    }
}
