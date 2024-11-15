package edu.ufsj.exception;

public class FalhaAutenticacaoUsuarioException extends Exception {

    public FalhaAutenticacaoUsuarioException() {
        super("Login ou senha incorretos");
    }

}
