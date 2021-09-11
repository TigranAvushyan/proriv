package com.application.proriv.security.jwt;

import com.application.proriv.security.config.JwtTokenConfig;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */

@AllArgsConstructor
@Slf4j
public class JwtVerifierFilter extends OncePerRequestFilter {

  private final JwtTokenUtil jwtTokenUtil;
  private final JwtTokenConfig jwtTokenConfig;


  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    String tokenWithBearer = request.getHeader(jwtTokenConfig.getHeader());

    if (tokenWithBearer == null ||
        tokenWithBearer.isEmpty() ||
        !tokenWithBearer.startsWith(jwtTokenConfig.getBearer())) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = tokenWithBearer.replace(jwtTokenConfig.getBearer(), "");

    Claims body = jwtTokenUtil.jwtParser(token);
    if (body == null) {
      filterChain.doFilter(request, response);
      return;
    }

    String username = body.getSubject();

    List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

    Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
        .map(m -> new SimpleGrantedAuthority(m.get(
            "authority")))
        .collect(Collectors.toSet());

    Authentication authentication = new UsernamePasswordAuthenticationToken(
        username,
        null,
        simpleGrantedAuthorities
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
