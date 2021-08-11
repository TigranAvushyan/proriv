package com.application.proriv.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */

@Data
@AllArgsConstructor
public class UserViewResponse {
  private String username;
  private String firstName;
  private String lastName;
}
