package com.projetos.back_chess_manager.dto.usuario;

import com.projetos.back_chess_manager.enums.TipoUsuario;
import com.projetos.back_chess_manager.enums.TituloJogador;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioDTO {

  @NotBlank
  private String username;
  @NotBlank
  private String senha;
  @NotBlank
  private String nome;
  @NotBlank
  private String sexo;
  @NotNull
  private Integer idade;

  @NotNull(message = "A role não pode ser nula")
  @Enumerated(EnumType.STRING)
  private TipoUsuario role;

  private Integer rating;
  private TituloJogador titulo;

}
