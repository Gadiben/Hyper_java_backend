package com.hyper.backend.security.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;

final class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  TokenAuthenticationFilter(final RequestMatcher requiresAuth) {
    super(requiresAuth);
  }

  @Override
  public Authentication attemptAuthentication(
    final HttpServletRequest request,
    final HttpServletResponse response) {
    final String param = ofNullable(request.getHeader("Authorization"))
      .orElse(request.getParameter("t"));

    final String token = ofNullable(param)
      .map(String::trim)
      .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));
    
    final Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
    System.out.println("In the filter");
    System.out.println(auth);
    try {
    	Authentication res = getAuthenticationManager().authenticate(auth);
        return res;
    }catch (AuthenticationException e) {
    	System.out.println(e);
    	throw e;
    }

  }

  @Override
  protected void successfulAuthentication(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final FilterChain chain,
    final Authentication authResult) throws IOException, ServletException {
	  System.out.println("Success in authentication");
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }
}