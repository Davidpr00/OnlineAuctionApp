package com.example.myebay.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.myebay.users.models.User;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
  public static final int expirationTime = 10 * 60 * 1000;
  public final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
  public final JWTVerifier verifier = JWT.require(algorithm).build();

  public String createAccess(User user, String request) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
        .withIssuer(request)
        .withClaim("role", user.getRolesString())
        .sign(algorithm);
  }

  public UsernamePasswordAuthenticationToken decodeToToken(String token) {
    DecodedJWT decodedJWT = verifier.verify(token);
    return new UsernamePasswordAuthenticationToken(
        decodedJWT.getSubject(), decodedJWT.getClaim("role"));
  }
}
