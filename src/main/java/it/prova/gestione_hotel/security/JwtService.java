package it.prova.gestione_hotel.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.prova.gestione_hotel.config.JwtConfig;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +  3_600_000))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
