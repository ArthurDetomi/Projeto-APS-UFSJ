package edu.ufsj.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.ufsj.model.Consulta;
import edu.ufsj.model.Medico;
import edu.ufsj.model.Paciente;
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
		if (idConsulta == null) {
			return false;
		}

		final String DELETE_CONSULTA_QUERY = "DELETE FROM consultas WHERE id = ?";

		int result = 0;

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONSULTA_QUERY);
			preparedStatement.setInt(1, idConsulta);
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return result == 1;
	}

	public boolean deleteConsultasByPacienteId(Integer pacienteId) {
		String DELETE_CONSULTA_QUERY = "" //
				+ "DELETE FROM consultas WHERE paciente_id = ?";

		int result = 0;

		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_CONSULTA_QUERY);
			preparedStatement.setInt(1, pacienteId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return result >= 1;
	}

	public boolean deleteConsultasByMedicoId(Integer medicoId) {
		String DELETE_CONSULTA_QUERY = "" //
				+ "DELETE FROM consultas WHERE medico_id = ?";

		int result = 0;

		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_CONSULTA_QUERY);
			preparedStatement.setInt(1, medicoId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return result >= 1;
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

	private Consulta extractConsultaFromResultSet(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id");
		Timestamp dataAgendamentoTimestamp = resultSet.getTimestamp("data_agendamento");
		Timestamp cadastradoTimestamp = resultSet.getTimestamp("cadastrado");
		Timestamp dataFimTimestamp = resultSet.getTimestamp("data_fim");

		LocalDateTime cadastrado = (cadastradoTimestamp != null) ? cadastradoTimestamp.toLocalDateTime() : null;
		LocalDateTime dataAgendamento = (dataAgendamentoTimestamp != null) ? dataAgendamentoTimestamp.toLocalDateTime()
				: null;
		LocalDateTime dataFim = (dataFimTimestamp != null) ? dataFimTimestamp.toLocalDateTime() : null;

		Integer medicoId = resultSet.getInt("medico_id");
		Integer pacienteId = resultSet.getInt("paciente_id");
		String descricao = resultSet.getString("descricao");
		String nomePaciente = resultSet.getString("nomePaciente");
		String cpfPaciente = resultSet.getString("cpfPaciente");
		String nomeMedico = resultSet.getString("usuarioNome");
		String crm = resultSet.getString("crm");

		Medico medico = new Medico();
		medico.setId(medicoId);
		medico.setNome(nomeMedico);
		medico.setCrm(crm);

		Paciente paciente = new Paciente();
		paciente.setId(pacienteId);
		paciente.setNome(nomePaciente);
		paciente.setCpf(cpfPaciente);

		Consulta consulta = new Consulta();
		consulta.setId(id);
		consulta.setMedico(medico);
		consulta.setPaciente(paciente);
		consulta.setDescricao(descricao);
		consulta.setDataAgendamento(dataAgendamento);
		consulta.setCadastrado(cadastrado);
		consulta.setDataFim(dataFim);

		return consulta;
	}

	public List<Consulta> findAllConsultasDeHoje() {
		List<Consulta> consultas = new ArrayList<>();

		final String FIND_ALL_CONSULTAS_DE_HOJE_QUERY = "" //
				+ "SELECT  " //
				+ "    c.*, p.nome as nomePaciente, p.cpf as cpfPaciente, u.nome as usuarioNome, m.crm as crm " //
				+ "FROM " //
				+ "    consultas AS c " //
				+ "        INNER JOIN " //
				+ "    pacientes AS p ON c.paciente_id = p.id " //
				+ "        INNER JOIN " //
				+ "    medicos AS m ON c.medico_id = m.id " //
				+ "        INNER JOIN " //
				+ "    usuarios AS u ON u.id = m.id " //
				+ "WHERE " //
				+ "    data_agendamento >= CURDATE() " //
				+ "        AND data_agendamento < CURDATE() + INTERVAL 1 DAY " //
				+ "ORDER BY data_agendamento ASC";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CONSULTAS_DE_HOJE_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				consultas.add(extractConsultaFromResultSet(resultSet));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return consultas;
	}

	public List<Consulta> findAll() {
		List<Consulta> consultas = new ArrayList<>();

		final String FIND_ALL_CONSULTAS_DE_HOJE_QUERY = "" //
				+ "SELECT  " //
				+ "    c.*, p.nome as nomePaciente, p.cpf as cpfPaciente, u.nome as usuarioNome, m.crm as crm " //
				+ "FROM " //
				+ "    consultas AS c " //
				+ "        INNER JOIN " //
				+ "    pacientes AS p ON c.paciente_id = p.id " //
				+ "        INNER JOIN " //
				+ "    medicos AS m ON c.medico_id = m.id " //
				+ "        INNER JOIN " //
				+ "    usuarios AS u ON u.id = m.id " //
				+ "ORDER BY data_agendamento DESC";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CONSULTAS_DE_HOJE_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				consultas.add(extractConsultaFromResultSet(resultSet));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return consultas;
	}

	public boolean finalizarConsulta(Consulta consulta) {
		if (consulta.getId() == null) {
			return false;
		}

		final String UPDATE_DATA_FIM_CONSULTA = "" //
				+ "UPDATE consultas   " //
				+ "SET   " //
				+ "    data_fim = ?  " //
				+ "WHERE  " //
				+ "    id = ?";

		int result = 0;

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DATA_FIM_CONSULTA);
			preparedStatement.setString(1, DateUtil.formatarParaSqlDateTime(LocalDateTime.now()));
			preparedStatement.setInt(2, consulta.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return result == 1;
	}

	public List<Consulta> findAllByStringSearch(String searchText) {
		final String FIND_ALL_BY_FIELDS = "" //
				+ "SELECT  " //
				+ "    c.*, p.nome as nomePaciente, p.cpf as cpfPaciente, u.nome as usuarioNome, m.crm as crm " //
				+ "FROM " //
				+ "    consultas AS c " //
				+ "        INNER JOIN " //
				+ "    pacientes AS p ON c.paciente_id = p.id " //
				+ "        INNER JOIN " //
				+ "    medicos AS m ON c.medico_id = m.id " //
				+ "        INNER JOIN " //
				+ "    usuarios AS u ON u.id = m.id " //
				+ "WHERE p.nome like ? or u.nome like ? or m.crm like ? or p.cpf like ? or c.data_agendamento like ?" //
				+ "ORDER BY data_agendamento DESC";

		String stringFromSearch = searchText + "%";

		List<Consulta> consultas = new ArrayList<>();

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_FIELDS);

			preparedStatement.setString(1, searchText);
			preparedStatement.setString(2, stringFromSearch);
			preparedStatement.setString(3, stringFromSearch);
			preparedStatement.setString(4, stringFromSearch);
			preparedStatement.setString(5, stringFromSearch);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				consultas.add(extractConsultaFromResultSet(resultSet));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return consultas;
	}
}
