package com.application.proriv.security.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */

@Data
@Component
public class JwtTokenConfig {

  private String header = HttpHeaders.AUTHORIZATION;

  @Value("${jwt.key}")
  private String key;

  @Value("${jwt.bearer}")
  private String bearer;

  public String getKey() {
    return Base64.getEncoder().encodeToString(key.getBytes());
  }
}
