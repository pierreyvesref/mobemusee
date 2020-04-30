package fr.miage.orleans.isi.projet.authentificationws.security;

import fr.miage.orleans.isi.projet.authentificationws.dto.RoleDTO;
import fr.miage.orleans.isi.projet.authentificationws.exceptions.TokenIncorrectException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import objects.users.Role;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

/*
    Creation des tokens
 */

@Component
public class JwtTokens {

    private static final long EXPIRATION_TIME = 10_000_000; //environ 2h45
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String login, List<RoleDTO> roles){

        //Parametrage des exigences du jwt
        Claims claims = Jwts.claims()
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        claims.put("roles", roles);

        //Encodage du JWS
        String jws = Jwts.builder()
                .setClaims(claims)
                .signWith(SECRET_KEY)
                .compact();

        return TOKEN_PREFIX + jws;
    }

    public String decodeToken(String token) throws TokenIncorrectException {

        if(token.startsWith(TOKEN_PREFIX)){
            token = token.replaceFirst(TOKEN_PREFIX, "");
        }

        try {
            Jws<Claims> jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            String login = jwsClaims.getBody().getSubject();
            List<Role> roles = jwsClaims.getBody().get("roles", List.class);

            return login;

        }catch (JwtException e){
            throw new TokenIncorrectException();
        }
    }
}
