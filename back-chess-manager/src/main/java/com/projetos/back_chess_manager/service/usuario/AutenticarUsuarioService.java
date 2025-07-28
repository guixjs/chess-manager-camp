package com.projetos.back_chess_manager.service.usuario;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projetos.back_chess_manager.dto.usuario.AutenticarUsuarioRequestDTO;
import com.projetos.back_chess_manager.enums.TipoUsuario;
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

    if (!passwordEncoder.matches(usuarioDTO.getSenha(), usuario.getSenha())) {
      throw new AuthenticationException();
    }

    List<String> roles = determinaUserRole(usuario.getRole());

    return gerarToken(usuario.getId(), roles);

  }

  private List<String> determinaUserRole(TipoUsuario tipoUsuario) {
    List<String> role = new ArrayList<>();
    if (tipoUsuario == TipoUsuario.JOGADOR) {
      role.add("JOGADOR");
    } else {
      role.add("ORGANIZADOR");
    }
    return role;
  }

  private String gerarToken(UUID idUser, List<String> role) {

    var expires = Instant.now().plus(Duration.ofMinutes(40));
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT.create().withIssuer("chess")
        .withSubject(idUser.toString())
        .withExpiresAt(expires)
        .withClaim("role", role.get(0))
        .sign(algorithm);

    return token;

  }
}
