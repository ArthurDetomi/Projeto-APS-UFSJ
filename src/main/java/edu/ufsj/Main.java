package edu.ufsj;

import edu.ufsj.dao.UsuarioDao;
import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        String login = "usuario123";
        String password = "senhaSecreta";
        String cpf = "123.456.789-00";
        String nome = "João Silva";
        String telefone = "(11) 98765-4321";
        String email = "joao.silva@example.com";
        LocalDateTime editado = LocalDateTime.now();  // Data e hora atuais
        TipoUsuario tipoUsuario = TipoUsuario.ATENDENTE;  // Tipo de usuário

        Usuario usuario = new Usuario(login, password, cpf, nome, telefone, email, editado, tipoUsuario);

        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.create(usuario)) {
            System.out.println("Usuario criado");
        } else {
            System.out.println("Usuario nao criado");
        }


    }
}