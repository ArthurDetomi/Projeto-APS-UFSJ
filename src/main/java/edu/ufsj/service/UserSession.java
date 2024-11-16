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
