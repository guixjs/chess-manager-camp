package com.projetos.back_chess_manager.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.back_chess_manager.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

  Optional<UsuarioEntity> findByUsername(String username);

}
