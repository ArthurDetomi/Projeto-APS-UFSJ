package edu.ufsj.controller;

import edu.ufsj.dao.PacienteDao;
import edu.ufsj.exception.PacienteJaExisteException;
import edu.ufsj.model.Paciente;

import java.util.List;

public class PacienteController {

	private final PacienteDao pacienteDao = new PacienteDao();

	public boolean cadastrarPaciente(Paciente paciente) throws PacienteJaExisteException {
		if (pacienteDao.existsByCpf(paciente.getCpf())) {
			throw new PacienteJaExisteException("Já existe um paciente com mesmo CPF cadastrado");
		}

		return pacienteDao.create(paciente);
	}

	public List<Paciente> listarPacientes() {
		return pacienteDao.findAll();
	}

	public boolean excluirPaciente(Integer idPaciente) {
		return pacienteDao.delete(idPaciente);
	}
}
