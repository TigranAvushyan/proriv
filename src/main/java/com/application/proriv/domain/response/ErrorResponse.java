package com.application.proriv.domain.response;


import lombok.Data;


/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 11/08/2021
 * @project - proriv
 */

@Data
public class ErrorResponse {
  private String message;
  private String path;

  public ErrorResponse(String message, String path) {
    this.message = message;
    this.path = path;
  }

}
