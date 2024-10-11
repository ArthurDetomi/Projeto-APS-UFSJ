package edu.ufsj.queries;

import edu.ufsj.config.DataBaseConfig;
import edu.ufsj.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoQueries {
    private Connection connection;
    private PreparedStatement selectAll;

    public MedicoQueries() {
        try {
            connection = DriverManager.getConnection(
                    DataBaseConfig.getURL(), DataBaseConfig.getUsername(), DataBaseConfig.getPassword()
            );

            selectAll = connection.prepareStatement(
                    "select * from medicos"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    public List<Medico> getAll() {
        List<Medico> medicos = null;

        ResultSet resultSet = null;
        try {
            resultSet = selectAll.executeQuery();
            medicos = new ArrayList<>();

            while (resultSet.next()) {
                medicos.add(
                        new Medico(
                                resultSet.getInt("id"),
                                resultSet.getString("nome")
                        )
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }

        return medicos;
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
