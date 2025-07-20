package com.projetos.back_chess_manager.model;

import com.projetos.back_chess_manager.enums.TituloJogador;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "jogador")
@SuperBuilder
public class JogadorEntity extends UsuarioEntity {

  @Enumerated(EnumType.STRING)
  private TituloJogador titulo;
  private Integer rating;

}
