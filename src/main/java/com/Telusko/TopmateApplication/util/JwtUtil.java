package com.Telusko.TopmateApplication.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Secret key for signing
    private static final long TOKEN_VALIDITY = 3600000; // 1 hour token validity

    /**
     * Generates a JWT for the provided user details.
     *
     * @param userDetails The user details containing username and roles.
     * @return A JWT token as a String.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().toString()); // Add roles to the claims

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // Use username (email) as the subject
                .setIssuedAt(new Date()) // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) // Expiration time
                .signWith(key) // Sign with the secret key
                .compact();
    }

    /**
     * Validates the JWT for username and role.
     *
     * @param token       The JWT token.
     * @param username    The expected username.
     * @param requiredRole The required role for validation.
     * @return true if valid; false otherwise.
     */
    public boolean validateToken(String token, String username, String requiredRole) {
        String extractedUsername = extractUsername(token);
        String extractedRole = extractRole(token);

        return extractedUsername.equals(username)
                && extractedRole.equals(requiredRole)
                && !isTokenExpired(token);
    }

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token The JWT token.
     * @return The username.
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extracts the role from the JWT token.
     *
     * @param token The JWT token.
     * @return The role as a String.
     */
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    /**
     * Checks if the token has expired.
     *
     * @param token The JWT token.
     * @return true if expired; false otherwise.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
