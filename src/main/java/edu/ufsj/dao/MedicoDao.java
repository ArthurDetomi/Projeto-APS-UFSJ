package edu.ufsj.dao;

import edu.ufsj.model.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicoDao extends AbstractGenericDao implements GenericDao<Medico> {
    @Override
    public boolean create(Medico medico) {
        final String CREATE_MEDICO_QUERY = "INSERT INTO medicos (id, crm) VALUES (?, ?)";

        int result = 0;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MEDICO_QUERY);
            preparedStatement.setInt(1, medico.getId());
            preparedStatement.setString(2, medico.getCrm());
            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result == 1;
    }
}
