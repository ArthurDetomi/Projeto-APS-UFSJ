package edu.ufsj.dao;

import java.sql.*;
import java.time.Instant;

import edu.ufsj.config.DataBaseConfig;
import edu.ufsj.model.Paciente;

public class PacienteDao implements GenericDao<Paciente> {

	private Connection connection;

	public PacienteDao() {
		try {
			connection = DriverManager.getConnection(DataBaseConfig.getURL(), DataBaseConfig.getUsername(),
					DataBaseConfig.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(Paciente paciente) {
		paciente.formatarCampos();

		final String CREATE_NEW_PACIENTE_QUERY = ""
				+ "INSERT INTO  pacientes (nome, cpf, telefone, cidade, estado, numero, editado) VALUES (?, ?, ?, ?, ?, ?, ?)";

		int result = 0;

		try {
			PreparedStatement createNewUsuarioStatement = connection.prepareStatement(CREATE_NEW_PACIENTE_QUERY);

			createNewUsuarioStatement.setString(1, paciente.getNome());
			createNewUsuarioStatement.setString(2, paciente.getCpf());
			createNewUsuarioStatement.setString(3, paciente.getTelefone());
			createNewUsuarioStatement.setString(4, paciente.getCidade());
			createNewUsuarioStatement.setString(5, paciente.getEstado());
			createNewUsuarioStatement.setString(6, paciente.getNumero());
			createNewUsuarioStatement.setTimestamp(7, Timestamp.from(Instant.now()));

			result = createNewUsuarioStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			close();
		}

		return result == 1;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

}
