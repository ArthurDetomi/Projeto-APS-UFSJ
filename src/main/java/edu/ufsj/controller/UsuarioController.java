package edu.ufsj.controller;

import edu.ufsj.dao.UsuarioDao;
import edu.ufsj.exception.FalhaAutenticacaoUsuarioException;
import edu.ufsj.exception.UsuarioJaExisteException;
import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;
import edu.ufsj.service.UserSession;

import java.util.List;

public class UsuarioController {

	private final UsuarioDao usuarioDao = new UsuarioDao();

	public void realizarLogin(String login, String password) throws FalhaAutenticacaoUsuarioException {
		Usuario usuario = usuarioDao.findByLoginAndPassword(login, password);

		if (usuario == null) {
			throw new FalhaAutenticacaoUsuarioException();
		}

		UserSession.getInstance().setLoggedUser(usuario);
	}


	public boolean cadastrarUsuario(Usuario usuario) throws UsuarioJaExisteException {
		if (usuarioDao.findIdByLogin(usuario.getLogin()) != null) {
			throw new UsuarioJaExisteException("Já existe um usuário com mesmo Login");
		}

		if (usuarioDao.existsByCpf(usuario.getCpf())) {
			throw new UsuarioJaExisteException("Já existe um usuário com mesmo CPF");
		}

		return usuarioDao.create(usuario);
	}

	public List<Usuario> listarAtendentes() {
		return usuarioDao.findAllByTipoUsuario(TipoUsuario.ATENDENTE);
	}

    public boolean excluirUsuario(Integer idUsuario) {
		return usuarioDao.delete(idUsuario);
    }
}
