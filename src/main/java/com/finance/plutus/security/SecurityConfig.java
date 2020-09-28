package com.finance.plutus.security;

import java.util.Arrays;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/** Plutus Created by catalin on 7/1/2020 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String ROLE_USER = "USER";

  private final PlutusAuthenticationEntryPoint authenticationEntryPoint;
  private final TokenAuthorizationFilter tokenAuthorizationFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.cors().configurationSource(createCorsConfigurationSource());

    http.authorizeRequests()
        .antMatchers(
            "/api/v1/login",
            "/api/v1/register",
            "/v2/api-docs",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/webjars/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/v1/users")
        .hasAuthority(ROLE_USER)
        .antMatchers("/api/v1/items/**", "/api/v1/partners/**", "/api/v1/invoices/**")
        .hasAuthority(ROLE_USER)
        .anyRequest()
        .denyAll();

    http.addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(authenticationEntryPoint)
        .authenticationEntryPoint(authenticationEntryPoint);

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private CorsConfigurationSource createCorsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedHeaders(Collections.singletonList("*"));
    configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
    configuration.setAllowedMethods(
        Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
