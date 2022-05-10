package com.application.proriv.domain.request.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */


@Getter
@Setter
public class AuthRequest {
  private String username;
  private String password;
}
