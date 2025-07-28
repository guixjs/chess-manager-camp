package com.projetos.back_chess_manager.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {

  @Value("${security.key.user}")
  private String secretKey;

  public String validarToken(String token) {
    token = token.replace("Bearer ", "");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      var subject = JWT.require(algorithm)
          .build()
          .verify(token)
          .getSubject();
      return subject;
    } catch (JWTVerificationException e) {
      e.printStackTrace();
      return "";
    }
  }

  public String getClaim(String token, String claimName) {
    token = token.replace("Bearer ", "");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      var claim = JWT.require(algorithm)
          .build()
          .verify(token)
          .getClaim(claimName)
          .asString();
      return claim;
    } catch (JWTVerificationException e) {
      e.printStackTrace();
      return "";
    }
  }
}
