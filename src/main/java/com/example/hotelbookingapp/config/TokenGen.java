package com.example.hotelbookingapp.config;

import com.example.hotelbookingapp.dto.JwtDto;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenGen {

    private final static Logger logger = LoggerFactory.getLogger(TokenGen.class);

    @Value("${jwt.secret}")
    private String secret;

    private static final int EXPIRATION_TIME = 60;

    public String generateToken (Authentication authentication) {
        org.springframework.security.core.userdetails.User user = (User) authentication.getPrincipal();
        List<String> roles = authentication.getAuthorities().stream().map(
                GrantedAuthority::getAuthority).collect(Collectors.toList());

        return Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .claim("roles", roles)
                .setExpiration(calculateExpirationDate(EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public String getEmailFromToken (String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String getIdFromToken (String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Boolean validateToken (String token) {
        try{
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e) {
            logger.error("Malformed Token");
        }catch (UnsupportedJwtException e) {
            logger.error("Unsupported Token");
        }catch (ExpiredJwtException e) {
            logger.error("Expired Token");
        }catch (IllegalArgumentException e) {
            logger.error("Illegal Token");
        }catch (SignatureException e) {
            logger.error("Signature exception");
        }
        return false;
    }

    public String refreshToken (JwtDto jwtDto) throws ParseException {
        JWT jwt = JWTParser.parse(jwtDto.getToken());
        JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
        String dni = claimsSet.getSubject();
        List<String> roles = (List<String>) claimsSet.getClaim("roles");

        return Jwts.builder().setSubject(dni)
                .setIssuedAt(new Date())
                .claim("roles", roles)
                .setExpiration(calculateExpirationDate(EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
