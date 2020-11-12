package br.com.payshare.utils.security.jwt;

import br.com.payshare.utils.security.services.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();


        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        }catch (SignatureException e ){
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }catch (MalformedJwtException e ){
            logger.error("Invalid JWT token: {}", e.getMessage());
        }catch (ExpiredJwtException e ){
            logger.error("JWT token is expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e ){
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e ){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getEmailFromJwt(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

}
