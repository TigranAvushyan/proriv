package com.application.proriv.domain.request;

import com.application.proriv.enums.UserStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 09/08/2021
 * @project - proriv
 */


@Data
public class CreateUserRequest {

  @NotBlank @Email
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

  @NotBlank
  private String rePassword;
}
