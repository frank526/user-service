package com.prueba.user_service.security.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Key;

@Service
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    //private final String secretKey = "mi_clave_secreta_segura_de_al_menos_32_bytes";

    private static final String SECRET_KEY = "mi_clave_secreta_segura_de_al_menos_32_bytes"; // Debe ser al menos de 32 caracteres
    private static final Key signingKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authHeader = request.getHeader("Authorization");

                if (authHeader != null && authHeader.startsWith("Bearer ")) {

                    String jwt = authHeader.substring(7);

                    try{
                        
                       // Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
                       Claims claims = extractClaims(jwt);
                        
                        String username = claims.getSubject();
                        
                        if (username != null) {
                            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, List.of());
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }


                    }catch(JwtException e){
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;

                    }
                }
                filterChain.doFilter(request, response);
            }

            public Claims extractClaims(String jwt) {
                return Jwts.parserBuilder()
                        .setSigningKey(signingKey) // Usamos parserBuilder()
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
            }
        }
