package edu.ufsj.controller;

import edu.ufsj.dao.UsuarioDao;
import edu.ufsj.exception.FalhaAutenticacaoUsuarioException;
import edu.ufsj.model.Usuario;
import edu.ufsj.service.UserSession;

public class UsuarioController {

	private UsuarioDao usuarioDao;

	public void realizarLogin(String login, String password) throws FalhaAutenticacaoUsuarioException {
		Usuario usuario = getUsuarioDao().findByLoginAndPassword(login, password);

		if (usuario == null) {
			throw new FalhaAutenticacaoUsuarioException();
		}

		UserSession.getInstance().setLoggedUser(usuario);
	}

	private UsuarioDao getUsuarioDao() {
		if (usuarioDao == null) {
			this.usuarioDao = new UsuarioDao();
		}
		return usuarioDao;
	}

	public boolean cadastrarUsuario(Usuario usuarioAtendente) {
		boolean success = getUsuarioDao().create(usuarioAtendente);
		return success;
	}
}
