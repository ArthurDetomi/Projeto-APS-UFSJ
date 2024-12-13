package edu.ufsj.persistence;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;

public class UsuarioDao extends AbstractGenericDao implements GenericDao<Usuario> {

	private final String FIND_BY_LOGIN_QUERY = "SELECT id FROM usuarios WHERE login = ?";

	@Override
	public boolean create(Usuario usuario) {
		final String CREATE_NEW_USUARIO_QUERY = "INSERT INTO usuarios (nome, cpf, telefone, email, login, password, tipo_usuario, editado)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		int result = 0;

		try (Connection connection = getConnection()) {
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

		try (Connection connection = getConnection()) {
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
		}

		return usuario;
	}

	/**
	 * @param login
	 * @return
	 */
	public Integer findIdByLogin(String login) {
		Integer id = null;

		try (Connection connection = getConnection()) {
			PreparedStatement findByLoginStatement = connection.prepareStatement(FIND_BY_LOGIN_QUERY);

			findByLoginStatement.setString(1, login);

			ResultSet resultSet = findByLoginStatement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return id;
	}

	public boolean existsByCpf(String cpf) {
		final String FIND_BY_CPF_QUERY = "SELECT id FROM usuarios WHERE cpf = ?";

		Integer id = null;
		try (Connection connection = getConnection()) {
			PreparedStatement findByCPFStatement = connection.prepareStatement(FIND_BY_CPF_QUERY);

			findByCPFStatement.setString(1, cpf);

			ResultSet resultSet = findByCPFStatement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return id != null;
	}

	public List<Usuario> findAllByTipoUsuario(TipoUsuario tipoUsuario) {
		final String FIND_ALL_BY_TIPO = "SELECT * FROM usuarios WHERE tipo_usuario = ? ORDER BY nome ASC";

		List<Usuario> usuarios = new ArrayList<>();

		try (Connection connection = getConnection()) {
			PreparedStatement findAllByTipoStatement = connection.prepareStatement(FIND_ALL_BY_TIPO);
			findAllByTipoStatement.setString(1, tipoUsuario.getTipo());

			ResultSet resultSet = findAllByTipoStatement.executeQuery();

			while (resultSet.next()) {
				usuarios.add(extractUserByResultSet(resultSet));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return usuarios;
	}

	public boolean delete(Integer idUsuario) {
		final String DELETE_BY_ID_QUERY = "DELETE FROM usuarios WHERE id = ?";

		int result = 0;

		try (Connection connection = getConnection()) {
			PreparedStatement deleteByIdStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);

			deleteByIdStatement.setInt(1, idUsuario);

			result = deleteByIdStatement.executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return result == 1;
	}

	public List<Usuario> findAllByTipoUsuarioAndStringSearch(TipoUsuario tipoUsuario, String searchText) {
		final String FIND_BY_TIPO_AND_FIELDS = "" //
				+ "SELECT  " //
				+ "    * " //
				+ "FROM " //
				+ "    usuarios " //
				+ "WHERE " //
				+ "    tipo_usuario = ? " //
				+ "        AND (nome LIKE ? OR cpf LIKE ? " //
				+ "        OR email LIKE ?) " + "ORDER BY nome ASC";

		List<Usuario> usuarios = new ArrayList<>();

		String stringFromSearch = searchText + "%";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TIPO_AND_FIELDS);
			preparedStatement.setString(1, tipoUsuario.getTipo());
			preparedStatement.setString(2, stringFromSearch);
			preparedStatement.setString(3, stringFromSearch);
			preparedStatement.setString(4, stringFromSearch);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				usuarios.add(extractUserByResultSet(resultSet));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		return usuarios;
	}
}
