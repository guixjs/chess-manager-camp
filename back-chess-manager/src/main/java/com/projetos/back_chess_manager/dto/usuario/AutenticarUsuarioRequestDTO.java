package com.projetos.back_chess_manager.dto.usuario;

import lombok.Data;

@Data
public class AutenticarUsuarioRequestDTO {
  private String username;
  private String senha;
}
