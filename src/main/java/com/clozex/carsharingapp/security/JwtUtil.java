package com.clozex.carsharingapp.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();
    private final Key secret;
    private final long expiration;

    public JwtUtil() {
        String secretString = getEnvVar("JWT_SECRET", "JFHJHFDJHFJKSH5345FJKSH"
                + "FJKH53453SVNMBZX&WYTUWY7657OUIOF"
                + "ASDFOPAIVJ34242562MLAWJIOQY");
        byte[] keyBytes = Base64.getEncoder().encode(secretString.getBytes(StandardCharsets.UTF_8));
        this.secret = Keys.hmacShaKeyFor(keyBytes);
        this.expiration = Long.parseLong(getEnvVar("JWT_EXPIRATION", "3600000"));
    }

    private static String getEnvVar(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null) {
            value = dotenv.get(key);
        }
        return (value != null) ? value : defaultValue;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);

            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT token");
        }
    }

    public String getUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
