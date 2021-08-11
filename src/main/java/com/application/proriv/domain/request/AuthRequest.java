package com.application.proriv.domain.request;

import lombok.Data;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */

@Data
public class AuthRequest {
  private String username;
  private String password;
}
