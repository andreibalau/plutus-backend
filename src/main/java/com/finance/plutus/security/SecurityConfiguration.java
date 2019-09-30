package com.finance.plutus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}") private String jwtSecret;
    @Autowired private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(corsConfigurationSource()).and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/api/users/login").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/users/register/email").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedHandler(failedAuthorizationEntryPoint())
                .authenticationEntryPoint(failedAuthorizationEntryPoint()).and()
                .apply(jwtFilterConfig()).and()
                .apply(jwtAuthorizationFilterConfig()).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FailedAuthorizationEntryPoint failedAuthorizationEntryPoint() {
        return new FailedAuthorizationEntryPoint();
    }

    @Bean
    public JwtTokenFilterConfig jwtAuthorizationFilterConfig() {
        return new JwtTokenFilterConfig(jwtTokenHelper());
    }

    @Bean
    public JwtAuthenticationFilterConfig jwtFilterConfig() throws Exception {
        return new JwtAuthenticationFilterConfig(new AntPathRequestMatcher("/api/users/login"),
                authenticationManager(), jwtTokenHelper(), authenticationFailureHandler());
    }

    @Bean
    public JwtTokenHelper jwtTokenHelper() {
        return new JwtTokenHelper(jwtSecret, userDetailsService);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new JsonAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
