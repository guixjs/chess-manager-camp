package com.projetos.back_chess_manager.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "organizador")
@NoArgsConstructor
public class OrganizadorEntity extends UsuarioEntity {
  @CreationTimestamp
  private LocalDateTime created_at;
}
