package com.application.proriv.domain.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */


@Getter @Setter
public class CreateUserRequest {

  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @NotBlank
  @Length(max = 11, min = 11)
  private String phone;
  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;
}
