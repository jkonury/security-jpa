package io.spring.securityjpa.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    final String header = "x-api-key";
    final String value = "apiKey";

    ApiKeyAuthFilter filter = new ApiKeyAuthFilter(header);
    filter.setAuthenticationManager(authentication -> {
      String principal = (String) authentication.getPrincipal();
      if (!value.equals(principal)) {
        throw new BadCredentialsException("The API key was not found or not the expected value.");
      }
      authentication.setAuthenticated(true);
      return authentication;
    });
    httpSecurity
      .antMatcher("/api/**")
      .csrf().disable()
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .addFilter(filter)
        .authorizeRequests()
          .anyRequest()
            .authenticated();
  }

}
