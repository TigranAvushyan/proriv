package com.application.proriv.security.jwt;

import com.application.proriv.security.config.JwtTokenConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil {

  private final JwtTokenConfig jwtTokenConfig;

  public boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(jwtTokenConfig.getKey()).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature - {}", ex.getMessage());
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token - {}", ex.getMessage());
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token - {}", ex.getMessage());
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token - {}", ex.getMessage());
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty - {}", ex.getMessage());
    }
    return false;
  }

  public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
    return jwtTokenConfig.getBearer() + Jwts.builder()
        .setSubject(username)
        .claim("authorities", authorities)
        .setIssuedAt(new Date())
        .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(3)))
        .signWith(SignatureAlgorithm.HS256, jwtTokenConfig.getKey())
        .compact();
  }

  public Claims jwtParser(String token) {
    Jws<Claims> claims = null;
    try {
      claims = Jwts.parser().setSigningKey(jwtTokenConfig.getKey()).parseClaimsJws(token);
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature - {}", ex.getMessage());
      return null;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token - {}", ex.getMessage());
      return null;
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token - {}", ex.getMessage());
      return null;
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token - {}", ex.getMessage());
      return null;
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty - {}", ex.getMessage());
      return null;
    }
    return claims.getBody();
  }
}
