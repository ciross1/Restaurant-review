package com.myself.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{



        http

                // all HTTP requests require authentication.
                //Every request to your API needs a valid user token.
                .authorizeHttpRequests(auth->
                auth.anyRequest().authenticated()

                )

                //Spring tells:
                // Clients must send a JWT token. Use my converter to understand who the user is
                // and what roles they have
                .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                ))


                // Sets session handling to stateless.
                //Stateless = the server does not store any user session.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Disables CSRF protection.
                // Stateless = the server does not store any user session.
                //Safe to do for APIs that are stateless and don’t use browser sessions.
                .csrf(csrf  ->
                        csrf.disable());


        return http.build();


    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        return new JwtAuthenticationConverter();
    }
}
