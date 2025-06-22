package com.pnc.project.config;

import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final UsuarioService usuarioService;
    private final JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,  HttpServletResponse response,  FilterChain filterChain)
        throws ServletException, IOException {
        
        try {
            String token = extractToken(request);
            if (token == null || jwtConfig.isTokenExpired(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            int idUser = jwtConfig.extracClaims(token).get("id", Integer.class);
            UsuarioResponse usuario = usuarioService.findById(idUser);

            UserDetails userDetails = User.builder()
                .username(usuario.getCorreo())
                .password("") // No necesitas la contraseña aquí
                .roles(usuario.getRol())
                .build();

            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(
                    userDetails, 
                    null, 
                    userDetails.getAuthorities());
            
            authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
        } catch (Exception e) {
            logger.error("Error en autenticación JWT", e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private String extractToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}