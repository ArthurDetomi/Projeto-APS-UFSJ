package edu.ufsj.dao;

import java.sql.*;
import java.time.LocalDateTime;

import edu.ufsj.model.Consulta;
import edu.ufsj.model.Medico;
import edu.ufsj.utils.DateUtil;

public class ConsultaDao extends AbstractGenericDao implements GenericDao<Consulta> {

	@Override
	public boolean create(Consulta consulta) {
		final String CREATE_CONSULTA_QUERY = "" //
				+ "INSERT INTO consultas ( medico_id, paciente_id, descricao, data_agendamento) VALUES ( ?, ?, ?, ? )";

		int result = 0;

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CONSULTA_QUERY);
			preparedStatement.setInt(1, consulta.getMedico().getId());
			preparedStatement.setInt(2, consulta.getPaciente().getId());
			preparedStatement.setString(3, consulta.getDescricao());
			preparedStatement.setString(4, DateUtil.formatarParaSqlDateTime(consulta.getDataAgendamento()));

			result = preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return result == 1;
	}

	@Override
	public boolean delete(Integer idConsulta) {
		return false;
	}

	public LocalDateTime findLastDataAgendamento(Consulta consulta) {
		Medico medico = consulta.getMedico();

		final String IS_DATA_AGENDAMENTO_DISPONIVEL_QUERY = "select MAX(data_agendamento) as data_hora_ultima_consulta_agendada from consultas where medico_id = ?";

		Timestamp lastDataAgendamento = null;

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(IS_DATA_AGENDAMENTO_DISPONIVEL_QUERY);

			preparedStatement.setInt(1, medico.getId());

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				lastDataAgendamento = resultSet.getTimestamp("data_hora_ultima_consulta_agendada");
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return (lastDataAgendamento != null) ? lastDataAgendamento.toLocalDateTime() : null;
	}
}
