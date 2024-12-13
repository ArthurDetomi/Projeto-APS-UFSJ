package edu.ufsj.controller;


import edu.ufsj.persistence.ConsultaDao;
import edu.ufsj.persistence.PacienteDao;
import edu.ufsj.exception.PacienteJaExisteException;
import edu.ufsj.model.Paciente;

import java.util.Collections;
import java.util.List;

public class PacienteController {

	private final PacienteDao pacienteDao = new PacienteDao();
	private final ConsultaDao consultaDao = new ConsultaDao();

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
		consultaDao.deleteConsultasByPacienteId(idPaciente);

		return pacienteDao.delete(idPaciente);
	}

	public List<Paciente> buscarPacientesByStringSearch(String pacienteSearchText) {
		if (pacienteSearchText.isBlank()) {
			return pacienteDao.findAll();
		}

		return pacienteDao.findByNomeOrCpf(pacienteSearchText);
	}
}
