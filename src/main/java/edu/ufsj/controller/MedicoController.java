package edu.ufsj.controller;

import edu.ufsj.dao.MedicoDao;
import edu.ufsj.dao.UsuarioDao;
import edu.ufsj.exception.UsuarioJaExisteException;
import edu.ufsj.model.Medico;

public class MedicoController {

    private UsuarioDao usuarioDao = new UsuarioDao();

    private MedicoDao medicoDao = new MedicoDao();

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

}
