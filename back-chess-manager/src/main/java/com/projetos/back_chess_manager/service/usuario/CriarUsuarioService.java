package com.projetos.back_chess_manager.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.back_chess_manager.dto.usuario.CriarUsuarioDTO;
import com.projetos.back_chess_manager.enums.TipoUsuario;
import com.projetos.back_chess_manager.exceptions.ExcecaoUsuarioEncontrado;
import com.projetos.back_chess_manager.model.JogadorEntity;
import com.projetos.back_chess_manager.model.OrganizadorEntity;
import com.projetos.back_chess_manager.model.UsuarioEntity;
import com.projetos.back_chess_manager.repository.UsuarioRepository;

@Service
public class CriarUsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public UsuarioEntity execute(CriarUsuarioDTO usuarioDTO) {
    this.usuarioRepository.findByUsername(usuarioDTO.getUsername())
        .ifPresent((user) -> {
          throw new ExcecaoUsuarioEncontrado();
        });

    TipoUsuario roleNovoUsuario = usuarioDTO.getRole();

    if (roleNovoUsuario == null) {
      throw new IllegalArgumentException("Role é obrigatório");
    }

    UsuarioEntity novoUsuario;

    switch (roleNovoUsuario) {
      case JOGADOR:
        if (usuarioDTO.getRating() == null) {
          throw new IllegalArgumentException("Rating é obrigatório");
        }
        novoUsuario = JogadorEntity.builder()
            .nome(usuarioDTO.getNome())
            .idade(usuarioDTO.getIdade())
            .username(usuarioDTO.getUsername())
            .senha(usuarioDTO.getSenha())
            .sexo(usuarioDTO.getSexo())
            .rating(usuarioDTO.getRating())
            .role(roleNovoUsuario)
            .titulo(usuarioDTO.getTitulo())
            .build();
        break;
      case ORGANIZADOR:
        novoUsuario = OrganizadorEntity.builder()
            .nome(usuarioDTO.getNome())
            .idade(usuarioDTO.getIdade())
            .username(usuarioDTO.getUsername())
            .senha(usuarioDTO.getSenha())
            .sexo(usuarioDTO.getSexo())
            .role(roleNovoUsuario)
            .build();
        break;

      default:
        throw new IllegalArgumentException("Role inválida: " + usuarioDTO.getRole());
    }
    return usuarioRepository.save(novoUsuario);
  }
}
