package edu.ufsj.dao;

import edu.ufsj.config.DataBaseConfig;
import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;

import java.sql.*;

public class UsuarioDao implements GenericDao<Usuario> {

    private Connection connection;

    public UsuarioDao() {
        try {
            connection = DriverManager.getConnection(
                    DataBaseConfig.getURL(), DataBaseConfig.getUsername(), DataBaseConfig.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean create(Usuario usuario) {
        final String CREATE_NEW_USUARIO_QUERY = "INSERT INTO usuarios (nome, cpf, telefone, email, login, password, tipo_usuario, editado)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        int result = 0;

        try {
            PreparedStatement createNewUsuarioStatement = connection.prepareStatement(
                    CREATE_NEW_USUARIO_QUERY
            );

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

        TipoUsuario tipoUsuario = TipoUsuario.getFromTipo(
                resultSet.getString("tipo_usuario")
        );

        Timestamp editado = resultSet.getTimestamp("editado");
        Timestamp cadastrado = resultSet.getTimestamp("cadastrado");
        String cpf = resultSet.getString("cpf");
        String telefone = resultSet.getString("telefone");
        String email = resultSet.getString("email");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String nome = resultSet.getString("nome");

        return new Usuario(
                id, login, password, cpf, nome, telefone, email, cadastrado.toLocalDateTime(), editado.toLocalDateTime(), tipoUsuario
        );
    }

    public Usuario findByLoginAndPassword(String login, String password) {
        final String FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM usuarios WHERE login = ? AND password = ?";

        Usuario usuario = null;

        try {
            PreparedStatement findByLoginAndPasswordStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD_QUERY);

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

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
