package com.application.proriv.security.config;

import com.application.proriv.security.jwt.JwtTokenUtil;
import com.application.proriv.security.jwt.JwtVerifierFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  securedEnabled = true,
  jsr250Enabled = true,
  prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;
  private final JwtTokenConfig jwtTokenConfig;
  private final BCryptPasswordEncoder pEncoder;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // Enable CORS and disable CSRF
      .cors().and()
      .csrf().disable()

      // Set session management to stateless
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()

      .authorizeRequests()

      // Public endpoints
      .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
      .antMatchers("/api/auth/**").permitAll()
      .antMatchers("/api/public/**").permitAll()

      // Our private endpoints
      .anyRequest().authenticated().and()

      // Add JWT token filter
//      .addFilter(new JwtTokenFilter(jwtTokenUtil, authenticationManager()))
//      .addFilterAfter(new JwtVerifierFilter(jwtTokenUtil, jwtTokenConfig), JwtTokenFilter.class);
      .addFilterBefore(new JwtVerifierFilter(jwtTokenUtil, jwtTokenConfig), UsernamePasswordAuthenticationFilter.class);
  }


  @Override
  protected void configure(
    AuthenticationManagerBuilder auth
  ) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(pEncoder);
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }


  // Used by spring security if CORS is enabled.
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }


  // Expose authentication manager bean
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}