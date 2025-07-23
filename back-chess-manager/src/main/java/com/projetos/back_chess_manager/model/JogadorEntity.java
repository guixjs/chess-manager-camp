package com.projetos.back_chess_manager.model;

import com.projetos.back_chess_manager.enums.TituloJogador;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "jogador")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class JogadorEntity extends UsuarioEntity {

  @Enumerated(EnumType.STRING)
  private TituloJogador titulo;
  private Integer rating;

}
