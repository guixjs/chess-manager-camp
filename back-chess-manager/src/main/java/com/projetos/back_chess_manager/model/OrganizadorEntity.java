package com.projetos.back_chess_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "organizador")
public class OrganizadorEntity extends UsuarioEntity {
}
