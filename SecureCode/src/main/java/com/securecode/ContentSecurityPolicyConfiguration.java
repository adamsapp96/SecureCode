package com.securecode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ContentSecurityPolicyConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http

          .authorizeHttpRequests(authorize -> authorize
              .anyRequest().permitAll()
          )
//          .headers(h -> h.contentSecurityPolicy( csp -> csp.policyDirectives("script-src 'self' ")))
          .csrf(c -> c.disable());

      return http.build();
  }




    
}

