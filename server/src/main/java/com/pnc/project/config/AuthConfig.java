package com.pnc.project.config;

import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public abstract class AuthConfig extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthConfig.class);

    private final UsuarioService service;
    private final JwtConfig jwt;

    protected AuthConfig(UsuarioService service, JwtConfig jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain filter) throws ServletException, IOException {
        try {

            //SecurityContextHolder sch
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                logger.debug("Authentication already present for request: {}", req.getRequestURI());
                return;
            }

            String token = extractToken(req);
            if (token == null || jwt.isTokenExpired(token)) {
                logger.warn("Invalid or expired token");
                return;
            }

            int idUser = jwt.extracClaims(token).get("id", Integer.class);
            UsuarioResponse user = service.findById(idUser);


            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getCorreo())
                    .password(user.getContrasena())
                    .roles(user.getRol())
                    .build();

            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

            WebAuthenticationDetailsSource wads = new WebAuthenticationDetailsSource();

            upat.setDetails(wads.buildDetails(req));

            SecurityContextHolder.getContext().setAuthentication(upat);
            logger.info("User authenticated: {}", user.getCorreo());
        } catch (Exception e) {
            logger.error("Error during JWT authentication", e);
        } finally {
            filter.doFilter(req, res);
            logger.info("Filter has been executed");
        }
    }

    private String extractToken(HttpServletRequest req) {
        final String BEARER_PREFIX = "Bearer ";
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) return authHeader.substring(BEARER_PREFIX.length());
        logger.debug("No JWT token found in request headers");
        return null;
    }
}