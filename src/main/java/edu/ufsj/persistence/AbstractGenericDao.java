package edu.ufsj.persistence;

import edu.ufsj.config.DataBaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractGenericDao {

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DataBaseConfig.getURL(), DataBaseConfig.getUsername(),
                    DataBaseConfig.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
