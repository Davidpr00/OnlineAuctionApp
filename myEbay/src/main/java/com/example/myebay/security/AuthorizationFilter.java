package com.example.myebay.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.myebay.common.exceptions.InvalidTokenException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

  @Component
  public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public AuthorizationFilter(JwtUtil jwtUtil) {
      this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
      if (request.getServletPath().contains("/login")
          || request.getServletPath().contains("/register")
          || request.getServletPath().contains("/reset-password")
          || request.getServletPath().contains("/email/verify/")
          || request.getServletPath().contains("/users/")) {
        filterChain.doFilter(request, response);
      } else {
        try {
          if (request.getHeader("token").isEmpty()) {
            throw new InvalidTokenException();
          } else {
            String token = request.getHeader("token");
            DecodedJWT decodedJwt = jwtUtil.verifier.verify(request.getHeader("token"));
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(decodedJwt.getClaim("role").toString()));
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                    decodedJwt.getSubject(),
                    null,
                    authorities
                ));
            if (decodedJwt.getClaim("role").toString().contains("USER") && !request.getServletPath().contains("USER")
                || decodedJwt.getClaim("role").toString().contains("ADMIN") && !request.getServletPath().contains("ADMIN"))  {
              throw new InvalidTokenException();
            }
            filterChain.doFilter(request, response);
          }
        } catch (Exception e) {
          filterChain.doFilter(request, response);
          throw new InvalidTokenException();
        }
      }
    }

}
