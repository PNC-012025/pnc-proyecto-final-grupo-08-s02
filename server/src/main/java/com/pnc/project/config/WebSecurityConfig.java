package com.pnc.project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pnc.project.dto.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public WebSecurityConfig(@Lazy JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    private String errorResponse(HttpStatus status, String message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(Response.build(status.value(), message, null)
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .headers(headers -> headers
                    .contentSecurityPolicy(csp -> csp
                        .policyDirectives("default-src 'self'; script-src 'self';")
                    )
                    .httpStrictTransportSecurity(hsts -> hsts
                        .maxAgeInSeconds(31536000)
                        .includeSubDomains(true)
                    )
                    .xssProtection(xss -> xss.disable())
                    .frameOptions(frame -> frame.deny())
                )

                // Configuración general
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Filtro JWT
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/api/usuarios/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/usuarios/save").permitAll()
                    .requestMatchers(HttpMethod.GET,
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                    ).permitAll()

                    // Endpoints para MANAGER
                    .requestMatchers(HttpMethod.GET, "/api/usuarios/list").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.GET, "/api/usuarios/rol").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/usuarios/delete/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/usuarios/update/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.GET, "/api/usuarios/codigo/**").hasRole("MANAGER")

                    // Endpoints para instructores
                    .requestMatchers(HttpMethod.GET, "/api/usuarios/materia").hasAnyRole("MANAGER", "INSTRUCTOR", "PAID_INSTRUCTOR")
                    .requestMatchers(HttpMethod.GET, "/api/usuarios/data/**").hasAnyRole("MANAGER", "INSTRUCTOR", "PAID_INSTRUCTOR")
                    .requestMatchers(HttpMethod.PATCH, "/api/usuarios/update/**").hasAnyRole("MANAGER", "INSTRUCTOR", "PAID_INSTRUCTOR")

                    .anyRequest().authenticated()
                )
                // Manejo de excepciones
                .exceptionHandling(exceptions -> exceptions
                    .accessDeniedHandler((req, res, ex) -> {
                        res.setStatus(HttpStatus.FORBIDDEN.value());
                        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        res.getWriter().write(errorResponse(HttpStatus.FORBIDDEN,
                            "Acceso denegado: No tienes los permisos necesarios"));
                    })
                    .authenticationEntryPoint((req, res, ex) -> {
                        res.setStatus(HttpStatus.UNAUTHORIZED.value());
                        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        res.getWriter().write(errorResponse(HttpStatus.UNAUTHORIZED,
                            "Autenticación requerida: Por favor inicie sesión"));
                    })
                )
                .build();
    }
}