package edu.ufsj.controller;

import java.util.List;

import edu.ufsj.persistence.ConsultaDao;
import edu.ufsj.persistence.MedicoDao;
import edu.ufsj.persistence.UsuarioDao;
import edu.ufsj.exception.UsuarioJaExisteException;
import edu.ufsj.model.Medico;

public class MedicoController {

	private final UsuarioDao usuarioDao = new UsuarioDao();

	private final MedicoDao medicoDao = new MedicoDao();

	private final ConsultaDao consultaDao = new ConsultaDao();

	public boolean cadastrarMedico(Medico medico) throws UsuarioJaExisteException {
		UsuarioController usuarioController = new UsuarioController();

		boolean usuarioFoiCadastrado = usuarioController.cadastrarUsuario(medico);

		if (!usuarioFoiCadastrado) {
			return false;
		}

		Integer id = usuarioDao.findIdByLogin(medico.getLogin());

		medico.setId(id);

		return medicoDao.create(medico);
	}

	public List<Medico> listarMedicos() {
		return medicoDao.findAll();
	}

	public boolean excluirMedico(Integer idMedico) {
		consultaDao.deleteConsultasByMedicoId(idMedico);

		boolean deletionSuccessful = medicoDao.delete(idMedico);

		if (!deletionSuccessful) {
			return false;
		}

		return usuarioDao.delete(idMedico);

	}

	public List<Medico> buscarMedicosByStringSearch(String medicoSearchText) {
		if (medicoSearchText.isBlank()) {
			return medicoDao.findAll();
		}

		return medicoDao.findByNomeOrCpfOrCrm(medicoSearchText);
	}
}
