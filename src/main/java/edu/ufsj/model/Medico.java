package edu.ufsj.model;

import java.time.LocalDateTime;
import java.util.List;

public class Medico extends Usuario {

	private String crm;
	private List<Consulta> consultas;

	public Medico(String login, String password, String cpf, String nome, String telefone, String email,
			LocalDateTime editado, String crm) {
		super(login, password, cpf, nome, telefone, email, editado, TipoUsuario.MEDICO);
		this.crm = crm;
	}

	public Medico(Integer id, TipoUsuario tipoUsuario, String cpf, String telefone, String email, String login,
			String password, String nome, String crm, LocalDateTime cadastrado, LocalDateTime editado) {
		super(id, login, password, cpf, nome, telefone, email, cadastrado, editado, tipoUsuario);
		this.crm = crm;
	}

	public Medico() {

	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
