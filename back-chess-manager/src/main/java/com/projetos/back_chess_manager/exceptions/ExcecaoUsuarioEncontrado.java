package com.projetos.back_chess_manager.exceptions;

public class ExcecaoUsuarioEncontrado extends RuntimeException {
  public ExcecaoUsuarioEncontrado() {
    super("Usuário já existe");
  }
}
