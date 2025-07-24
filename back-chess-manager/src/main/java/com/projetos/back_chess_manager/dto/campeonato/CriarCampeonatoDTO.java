package com.projetos.back_chess_manager.dto.campeonato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarCampeonatoDTO {

  private String nomeCampeonato;
  private Integer qtdJogadores;
}
