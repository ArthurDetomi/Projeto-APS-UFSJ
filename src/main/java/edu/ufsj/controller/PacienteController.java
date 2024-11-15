package edu.ufsj.controller;

import edu.ufsj.dao.PacienteDao;
import edu.ufsj.model.Paciente;

public class PacienteController {

	private PacienteDao pacienteDao;

	public boolean cadastrarPaciente(Paciente paciente) {
		return getPacienteDao().create(paciente);
	}

	public PacienteDao getPacienteDao() {
		if (pacienteDao == null) {
			pacienteDao = new PacienteDao();
		}
		return pacienteDao;
	}
}
