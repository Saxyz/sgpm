package edu.unimag.sgpm.control.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import edu.unimag.sgpm.control.security.service.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expirationMs}")
    private String jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + Long.parseLong(jwtExpirationMs)))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateJwtToken(String authToken)  {
        try {
            Jwts.parser()
                    .setSigningKey(key())
                    .build()
                    .parse(authToken);
            return true;
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.info(e.getMessage());
            log.error(e.getMessage());
            return false;
        }
    }

    public String getUserNameFromJwtToken(String authToken) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
