package edu.ufsj.dao;

import edu.ufsj.model.Consulta;

public class ConsultaDao extends AbstractGenericDao implements GenericDao<Consulta> {

    @Override
    public boolean create(Consulta consulta) {
        return false;
    }

    @Override
    public boolean delete(Integer idConsulta) {
        return false;
    }

}
