package com.finance.plutus.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/** Plutus Created by Catalin on 9/29/2020 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  public static final String RESOURCE_ID = "plutus-resource-api";
  private static final String ROLE_USER = "ROLE_USER";

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors()
        .configurationSource(createConfigurationSource())
        .and()
        .authorizeRequests()
        .antMatchers("/api/v1/register")
        .permitAll()
        .antMatchers("/api/v1/**")
        .hasAnyAuthority(ROLE_USER)
        .anyRequest()
        .denyAll()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        .accessDeniedHandler(new CustomAccessDeniedHandler());
  }

  private CorsConfigurationSource createConfigurationSource() {
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
