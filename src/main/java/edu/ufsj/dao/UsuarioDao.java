package edu.ufsj.dao;

import edu.ufsj.config.DataBaseConfig;
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

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
