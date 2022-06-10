package com.luladjiev.nocturnal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
    throws Exception {
    httpSecurity
      .exceptionHandling(exceptionHandling ->
        exceptionHandling.authenticationEntryPoint(
          new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
        )
      )
      .formLogin(login ->
        login
          .loginPage("/login")
          .loginProcessingUrl("/api/login")
          .permitAll()
          .failureHandler(new SimpleUrlAuthenticationFailureHandler())
          .successHandler((request, response, authentication) ->
            response.setStatus(HttpStatus.NO_CONTENT.value())
          )
      )
      .logout(logout ->
        logout
          .logoutUrl("/api/logout")
          .permitAll()
          .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
      )
      .authorizeHttpRequests(requests ->
        requests.antMatchers("/api/**").authenticated()
      )
      .csrf(csrf ->
        csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
      );

    return httpSecurity.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    var user = User
      .builder()
      .passwordEncoder(encoder::encode)
      .username("admin")
      .password("123")
      .roles("ADMIN")
      .build();

    return new InMemoryUserDetailsManager(user);
  }
}
