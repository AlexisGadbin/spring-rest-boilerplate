package fr.gadbin.springrestboilerplate.application.service;

import java.security.Key;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.gadbin.springrestboilerplate.application.model.User;
import fr.gadbin.springrestboilerplate.application.port.in.JwtUtilsUseCase;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtilsService implements JwtUtilsUseCase {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Value("${jwt.validity.in.seconds}")
    private int jwtValidityInSeconds;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        final String userName = extractUserName(token);
        return (userName.equals(user.email())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, User user) {

        return Jwts.builder().setClaims(extraClaims).setSubject(user.email())
                .setIssuedAt(Date.from(OffsetDateTime.now().toInstant()))
                .setExpiration(Date.from(OffsetDateTime.now().plusSeconds(jwtValidityInSeconds).toInstant()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(Date.from(OffsetDateTime.now().toInstant()));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
