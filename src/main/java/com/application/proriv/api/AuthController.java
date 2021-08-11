package com.application.proriv.api;

import com.application.proriv.domain.model.User;
import com.application.proriv.domain.request.AuthRequest;
import com.application.proriv.domain.request.CreateUserRequest;
import com.application.proriv.domain.response.ErrorResponse;
import com.application.proriv.domain.response.UserViewResponse;
import com.application.proriv.security.jwt.JwtTokenUtil;
import com.application.proriv.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 11/08/2021
 * @project - proriv
 */


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationManager authenticationManager;

  @PostMapping("registration")
  public ResponseEntity<?> registration(@RequestBody @Valid CreateUserRequest request) {
    try {
      User user = userService.create(request);
      UserViewResponse body = new UserViewResponse(user.getUsername(),
                                                   user.getFirstName(),
                                                   user.getLastName());

      return ResponseEntity.ok().body(body);

    } catch (EntityExistsException e) {

      ErrorResponse body = new ErrorResponse(e.getMessage(), "/api/public/registration");

      return ResponseEntity
        .status(CONFLICT)
        .body(body);
    }
  }

  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
    try {
      Authentication authenticate = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(
          request.getUsername(), request.getPassword()
        ));
      String token = jwtTokenUtil.createToken(request.getUsername(), authenticate.getAuthorities());

      return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    } catch (AuthenticationException e) {
      String message = "Wrong username or password";
      log.warn(message);
      SecurityContextHolder.clearContext();
      ErrorResponse body = new ErrorResponse(message, "/api/public/login");
      return ResponseEntity.status(UNAUTHORIZED).body(body);
    }
  }

  @GetMapping("logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    securityContextLogoutHandler.logout(request, response, null);
  }

}
