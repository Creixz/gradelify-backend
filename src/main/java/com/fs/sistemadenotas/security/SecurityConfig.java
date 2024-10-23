package com.fs.sistemadenotas.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(HttpMethod.POST, "/gradelify/v1/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/gradelify/v1/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/gradelify/v1/prueba")
                        .hasAnyAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/gradelify/v1/classrooms").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/gradelify/v1/classrooms").hasAuthority("ADMINISTRADOR")
                        .anyRequest().permitAll())
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
