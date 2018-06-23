package br.harlan.sbi.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")

    private Long expirationDays;

    private Long millisToDay = 86400000L;

    public JWTUtil() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    public String generateToken(String username) {
        LOGGER.info("JWTUtil. Username: {}", username);
        LOGGER.info("JWTUtil. Secret: {}. Expiration: {}", secret, expirationDays);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationDays * millisToDay))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}
