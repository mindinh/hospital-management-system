package com.udpt.accounts.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtHelper {

    @Value("${jwt.key}")
    private String keyJwt;

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyJwt));

        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public boolean decrypt(String token) {
        boolean res = false;

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyJwt));

        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            res = true;
        } catch (Exception e) {
            System.out.println("decrypt error " + e.getMessage());

        }

        return res;
    }

    public String getDataToken(String token) {
        String role = "";

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyJwt));

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            // lưu cái role ở đâu thì get ở đó (subject(u.getRole))
            role = claims.get("role", String.class);
            // role = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();

        } catch (Exception e) {
            System.out.println("get data token error " + e.getMessage());

        }

        return role;
    }

    public String getId(String token) {
        String id = "";

        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(keyJwt));

        try {
            id = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();

        } catch (Exception e) {
            System.out.println("get data token error " + e.getMessage());

        }

        return id;
    }
}
