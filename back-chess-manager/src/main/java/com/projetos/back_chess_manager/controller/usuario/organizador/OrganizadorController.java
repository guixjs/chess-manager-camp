package com.projetos.back_chess_manager.controller.usuario.organizador;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.back_chess_manager.dto.campeonato.CriarCampeonatoDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/org")
public class OrganizadorController {

  @PostMapping("/new/camp")
  public void createCamp(@Valid @RequestBody CriarCampeonatoDTO campeonatoDTO,
      HttpServletRequest request) {
    var idUser = UUID.fromString(request.getAttribute("idUsuario").toString());
    System.out.println(idUser);

  }

}
