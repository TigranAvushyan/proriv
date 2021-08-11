package com.application.proriv.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 11/08/2021
 * @project - proriv
 */


@Component
public class PasswordConfig {

  @Bean
  public BCryptPasswordEncoder pEncoder() {
    return new BCryptPasswordEncoder(12);
  }
}
