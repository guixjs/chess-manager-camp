package com.projetos.back_chess_manager.model;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.projetos.back_chess_manager.enums.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  @NotNull(message = "O username não pode ser nulo")
  private String username;

  @Length(min = 6, max = 100, message = "Senha deve ter 6 a 100 caracteres")
  private String senha;

  @NotBlank(message = "O nome é obrigatório")
  @Column(nullable = false)
  @Pattern(regexp = "^.{1,50}$", message = "O nome deve ter no máximo 50 caracteres")
  private String nome;

  @NotBlank(message = "O sexo é obrigatório")
  private String sexo;

  private Integer idade;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, updatable = false)
  private TipoUsuario role;

  @CreationTimestamp
  private LocalDate created_at;
}
