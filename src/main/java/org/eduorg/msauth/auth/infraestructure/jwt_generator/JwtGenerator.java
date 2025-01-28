package org.eduorg.msauth.auth.infraestructure.jwt_generator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.eduorg.msauth.common.application.jwt_generator.IJwtGenerator;
import org.eduorg.msauth.common.application.jwt_generator.dto.DataJwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtGenerator implements IJwtGenerator<DataJwt, String> {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwt_expiration;

    @Override
    public String generate(DataJwt param) {
        return buildToken(param, jwt_expiration);
    }

    private String buildToken(DataJwt data, final long expiration) {

        return Jwts.builder()
                .id(data.getId().toString())
                .subject(data.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] secretBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
