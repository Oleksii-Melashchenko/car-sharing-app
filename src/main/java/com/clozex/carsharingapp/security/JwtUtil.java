package com.clozex.carsharingapp.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String DEFAULT_SECRET =
            "JFHJHFDJHFJKSH5345FJKSHFJKH53453SVNMBZX&WYTUWY7657OUIOFASDFOPAIVJ34242562MLAWJIOQY";
    private static final long DEFAULT_EXPIRATION = 3600000;
    private static final Dotenv dotenv = loadDotenv();
    private final Key secret;
    private final long expiration;

    public JwtUtil() {
        this.secret = generateKey(getEnvVar("JWT_SECRET", DEFAULT_SECRET));
        this.expiration = Long.parseLong(getEnvVar("JWT_EXPIRATION",
                String.valueOf(DEFAULT_EXPIRATION)));
    }

    private static Dotenv loadDotenv() {
        return Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }

    private static String getEnvVar(String key, String defaultValue) {
        return Optional.ofNullable(System.getenv(key))
                .or(() -> Optional.ofNullable(dotenv.get(key)))
                .orElse(defaultValue);
    }

    private static Key generateKey(String secretString) {
        byte[] keyBytes = secretString.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
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
