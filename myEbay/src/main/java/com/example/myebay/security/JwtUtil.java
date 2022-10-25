package com.example.myebay.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.myebay.users.models.User;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
  public final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
  public final JWTVerifier verifier = JWT.require(algorithm).build();

  public static final int expirationTime = 10 * 60 * 1000;

  public String createAccess(User user, String request) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
        .withIssuer(request)
        .withClaim("role", user.getRolesString())
        .sign(algorithm);
  }
}