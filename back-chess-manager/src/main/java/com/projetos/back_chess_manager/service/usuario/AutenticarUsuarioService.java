package com.projetos.back_chess_manager.service.usuario;

import java.time.Duration;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projetos.back_chess_manager.dto.usuario.AutenticarUsuarioRequestDTO;
import com.projetos.back_chess_manager.repository.UsuarioRepository;

@Service
public class AutenticarUsuarioService {

  @Value("${security.key.user}")
  private String secretKey;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AutenticarUsuarioRequestDTO usuarioDTO) throws AuthenticationException {

    var usuario = this.usuarioRepository.findByUsername(usuarioDTO.getUsername())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Usuário ou senha inválida");
        });

    var verificarSenha = passwordEncoder.matches(usuarioDTO.getSenha(), usuario.getSenha());

    if (!verificarSenha) {
      throw new AuthenticationException();
    }
    var expires = Instant.now().plus(Duration.ofMinutes(40));
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT.create().withIssuer("chess")
        .withSubject(usuario.getId().toString())
        .withExpiresAt(expires)
        .sign(algorithm);

    return token;
  }
}
