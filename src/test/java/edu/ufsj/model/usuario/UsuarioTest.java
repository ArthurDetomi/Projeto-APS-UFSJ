package edu.ufsj.model.usuario;

import java.time.LocalDateTime;

import edu.ufsj.persistence.UsuarioDao;
import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;
import edu.ufsj.service.UserSession;

public class UsuarioTest {

    public void testCadastroUsuario() {
        String login = "diogo";
        String password = "diogosenha";
        String cpf = "737.986.575-72";
        String nome = "Diogo Diogo Duarte";
        String telefone = "(11) 98765-4321";
        String email = "diogo.diogo.duarte@dddrin.com.br";
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

    public void testLoginUsuario() {
        String login = "diogo";
        String password = "diogosenha";

        UsuarioDao usuarioDao = new UsuarioDao();

        Usuario usuario = usuarioDao.findByLoginAndPassword(login, password);

        if (usuario == null) {
            System.out.println("Usuario nao cadastrado");
            System.exit(1);
        } else {
            System.out.println(usuario);

            UserSession.getInstance().setLoggedUser(usuario);
        }


        System.out.println("Usuário da sessão: " + UserSession.getInstance().getLoggedUser().getNome());
    }
}
