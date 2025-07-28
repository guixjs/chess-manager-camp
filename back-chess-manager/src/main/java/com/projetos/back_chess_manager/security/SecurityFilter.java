package com.projetos.back_chess_manager.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projetos.back_chess_manager.provider.JWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private JWTProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    SecurityContextHolder.getContext().setAuthentication(null);

    if (header != null) {
      var subjectToken = this.jwtProvider.validarToken(header);

      if (subjectToken.isEmpty()) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }

      var roleToken = jwtProvider.getClaim(header, "role");
      if (roleToken == null || roleToken.isEmpty()) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return;
      }

      List<SimpleGrantedAuthority> auths = List.of(
          new SimpleGrantedAuthority("ROLE_" + roleToken));

      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          subjectToken,
          null,
          auths);
      SecurityContextHolder.getContext().setAuthentication(auth);
      request.setAttribute("idUsuario", subjectToken);
    }
    filterChain.doFilter(request, response);
  }

}
