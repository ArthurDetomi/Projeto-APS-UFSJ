package edu.ufsj.service;

import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;

public class UserSession {
	private static UserSession instance;
	private Usuario loggedUser;

	public UserSession() {

	}

	public static UserSession getInstance() {
		if (instance == null) {
			instance = new UserSession();
		}
		return instance;
	}

	public boolean isUsuarioLogadoPodeCadastrarPaciente() {
		TipoUsuario tipoUsuario = getLoggedUser().getTipoUsuario();
		return tipoUsuario.equals(TipoUsuario.ADMIN) || tipoUsuario.equals(TipoUsuario.ATENDENTE);
	}

	public boolean isUsuarioLogadoPodeCadastrarMedico() {
		TipoUsuario tipoUsuario = getLoggedUser().getTipoUsuario();
		return tipoUsuario.equals(TipoUsuario.ADMIN) || tipoUsuario.equals(TipoUsuario.ATENDENTE);
	}

	public boolean isUsuarioLogadoPodeCadastrarConsultaMedica() {
		TipoUsuario tipoUsuario = getLoggedUser().getTipoUsuario();
		return tipoUsuario.equals(TipoUsuario.ADMIN) || tipoUsuario.equals(TipoUsuario.ATENDENTE);
	}

	public boolean isUsuarioLogadoPodeCadastrarAtendente() {
		TipoUsuario tipoUsuario = getLoggedUser().getTipoUsuario();
		return tipoUsuario.equals(TipoUsuario.ADMIN);
	}

	public boolean isUsuarioPodeExcluirByTipo(TipoUsuario tipoUsuario) {
		TipoUsuario tipoUsuarioLogged = loggedUser.getTipoUsuario();

		switch (tipoUsuario) {
			case ATENDENTE:
				return  tipoUsuarioLogged.equals(TipoUsuario.ADMIN);
			case MEDICO:
				return tipoUsuarioLogged.equals(TipoUsuario.ADMIN) || tipoUsuarioLogged.equals(TipoUsuario.ATENDENTE);
		}

		return false;
	}

	public Usuario getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Usuario loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void clearSession() {
		this.loggedUser = null;
	}
}
