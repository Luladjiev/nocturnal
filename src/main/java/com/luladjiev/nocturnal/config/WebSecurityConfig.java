package com.luladjiev.nocturnal.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.filter.OncePerRequestFilter;

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
          .failureHandler(new SimpleUrlAuthenticationFailureHandler())
          .successHandler((request, response, authentication) -> {
            var csrfToken = (CsrfToken) request.getAttribute(
              CsrfToken.class.getName()
            );
            var cookie = new Cookie("XSRF-TOKEN", csrfToken.getToken());
            cookie.setPath("/");

            response.addCookie(cookie);
            response.setStatus(HttpStatus.NO_CONTENT.value());
          })
      )
      .logout(logout ->
        logout
          .logoutUrl("/api/logout")
          .deleteCookies("JSESSIONID")
          .logoutSuccessHandler((request, response, authentication) ->
            response.setStatus(HttpStatus.NO_CONTENT.value())
          )
      )
      .authorizeHttpRequests(requests ->
        requests
          .requestMatchers("/api/**")
          .authenticated()
          .anyRequest()
          .permitAll()
      )
      .csrf(csrf -> {
        var csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        var requestHandler = new CsrfTokenRequestAttributeHandler();

        csrf
          .csrfTokenRepository(csrfTokenRepository)
          .csrfTokenRequestHandler(requestHandler);
      })
      .addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class);

    return httpSecurity.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    PasswordEncoder encoder =
      PasswordEncoderFactories.createDelegatingPasswordEncoder();

    var user = User
      .builder()
      .passwordEncoder(encoder::encode)
      .username("admin")
      .password("123")
      .roles("ADMIN")
      .build();

    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public CookieSameSiteSupplier cookieSameSiteSupplier() {
    return CookieSameSiteSupplier.ofLax();
  }
}

final class CsrfCookieFilter extends OncePerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(CsrfCookieFilter.class);

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    var csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    // Render the token value to a cookie by causing the deferred token to be loaded
    csrfToken.getToken();
    filterChain.doFilter(request, response);
    logger.debug("Added CSRF Token");
  }
}
