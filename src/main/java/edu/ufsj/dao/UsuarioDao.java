package edu.ufsj.dao;

import java.sql.*;
import java.time.LocalDateTime;

import edu.ufsj.config.DataBaseConfig;
import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;

public class UsuarioDao implements GenericDao<Usuario> {

	private Connection connection;

	public UsuarioDao() {
		try {
			connection = DriverManager.getConnection(DataBaseConfig.getURL(), DataBaseConfig.getUsername(),
					DataBaseConfig.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(Usuario usuario) {
		final String CREATE_NEW_USUARIO_QUERY = "INSERT INTO usuarios (nome, cpf, telefone, email, login, password, tipo_usuario, editado)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		int result = 0;

		try {
			PreparedStatement createNewUsuarioStatement = connection.prepareStatement(CREATE_NEW_USUARIO_QUERY);

			createNewUsuarioStatement.setString(1, usuario.getNome());
			createNewUsuarioStatement.setString(2, usuario.getCpf());
			createNewUsuarioStatement.setString(3, usuario.getTelefone());
			createNewUsuarioStatement.setString(4, usuario.getEmail());
			createNewUsuarioStatement.setString(5, usuario.getLogin());
			createNewUsuarioStatement.setString(6, usuario.getPassword());
			createNewUsuarioStatement.setString(7, usuario.getTipoUsuario().getTipo());
			createNewUsuarioStatement.setTimestamp(8, Timestamp.valueOf(usuario.getEditado()));

			result = createNewUsuarioStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			close();
		}

		return result == 1;
	}

	private Usuario extractUserByResultSet(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id");

		TipoUsuario tipoUsuario = TipoUsuario.getFromTipo(resultSet.getString("tipo_usuario"));

		Timestamp editadoTimestamp = resultSet.getTimestamp("editado");
		Timestamp cadastradoTimestamp = resultSet.getTimestamp("cadastrado");
		String cpf = resultSet.getString("cpf");
		String telefone = resultSet.getString("telefone");
		String email = resultSet.getString("email");
		String login = resultSet.getString("login");
		String password = resultSet.getString("password");
		String nome = resultSet.getString("nome");

		LocalDateTime cadastrado = (cadastradoTimestamp == null) ? null : cadastradoTimestamp.toLocalDateTime();
		LocalDateTime editado = (editadoTimestamp == null) ? null : editadoTimestamp.toLocalDateTime();

		return new Usuario(id, login, password, cpf, nome, telefone, email, cadastrado, editado, tipoUsuario);
	}

	public Usuario findByLoginAndPassword(String login, String password) {
		final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM usuarios WHERE login = ? AND password = ?";

		Usuario usuario = null;

		try {
			PreparedStatement findByLoginAndPasswordStatement = connection
					.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_QUERY);

			findByLoginAndPasswordStatement.setString(1, login);
			findByLoginAndPasswordStatement.setString(2, password);

			ResultSet resultSet = findByLoginAndPasswordStatement.executeQuery();

			if (resultSet.next()) {
				usuario = extractUserByResultSet(resultSet);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			close();
		}

		return usuario;
	}

	public boolean existsByLogin(String login) {
		final String FIND_BY_LOGIN_QUERY = "SELECT id FROM usuarios WHERE login = ?";

		Integer id = null;
		try {
			PreparedStatement findByLoginStatement = connection.prepareStatement(FIND_BY_LOGIN_QUERY);

			findByLoginStatement.setString(1, login);

			ResultSet resultSet = findByLoginStatement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			close();
		}

		return id != null;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}

	public boolean existsByCpf(String cpf) {
		final String FIND_BY_CPF_QUERY = "SELECT id FROM usuarios WHERE cpf = ?";

		Integer id = null;
		try {
			PreparedStatement findByCPFStatement = connection.prepareStatement(FIND_BY_CPF_QUERY);

			findByCPFStatement.setString(1, cpf);

			ResultSet resultSet = findByCPFStatement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally {
			close();
		}

		return id != null;
	}
}
