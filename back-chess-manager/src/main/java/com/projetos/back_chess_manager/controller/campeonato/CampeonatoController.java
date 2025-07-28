package com.projetos.back_chess_manager.controller.campeonato;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.back_chess_manager.dto.campeonato.CriarCampeonatoDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/camp")
public class CampeonatoController {

  @PostMapping("/new")
  @PreAuthorize("hasRole('ORGANIZADOR')")
  public void createCamp(@Valid @RequestBody CriarCampeonatoDTO campeonatoDTO,
      HttpServletRequest request) {
    try {
      var idUser = UUID.fromString(request.getAttribute("idUsuario").toString());
      System.out.println(idUser);

    } catch (Exception e) {
      e.printStackTrace();

    }
  }

  @PostMapping("/apply")
  @PreAuthorize("hasRole('JOGADOR')")
  public void applyCamp(HttpServletRequest request) {
    try {
      var idUser = UUID.fromString(request.getAttribute("idUsuario").toString());
      System.out.println(idUser);

    } catch (Exception e) {
      e.printStackTrace();

    }
  }

}
