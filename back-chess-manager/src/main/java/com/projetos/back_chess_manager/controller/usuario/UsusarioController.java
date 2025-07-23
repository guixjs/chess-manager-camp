package com.projetos.back_chess_manager.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.back_chess_manager.dto.usuario.AutenticarUsuarioRequestDTO;
import com.projetos.back_chess_manager.dto.usuario.CriarUsuarioDTO;
import com.projetos.back_chess_manager.service.usuario.AutenticarUsuarioService;
import com.projetos.back_chess_manager.service.usuario.CriarUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/user")
public class UsusarioController {

  @Autowired
  private CriarUsuarioService criarUsuarioService;

  @Autowired
  private AutenticarUsuarioService autenticarUsuarioService;

  @PostMapping("/new")
  public ResponseEntity<Object> createUser(@Valid @RequestBody CriarUsuarioDTO usuarioDTO) {
    try {
      var resultado = this.criarUsuarioService.execute(usuarioDTO);
      return ResponseEntity.ok().body(resultado);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/auth")
  public ResponseEntity<Object> authUser(@Valid @RequestBody AutenticarUsuarioRequestDTO usuarioDTO) {
    try {
      var resultado = this.autenticarUsuarioService.execute(usuarioDTO);
      return ResponseEntity.ok().body(resultado);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
