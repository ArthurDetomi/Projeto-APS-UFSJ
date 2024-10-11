package edu.ufsj.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseConfig {
    private static String URL;
    private static String username;
    private static String password;

    static {
        try (
                InputStream file = DataBaseConfig.class.getClassLoader().getResourceAsStream("db.properties");
        ) {
            if (file == null) {
                System.err.println("db.properties not found. Exiting the application.");
                System.exit(2);
            }

            Properties properties = new Properties();
            properties.load(file);

            URL = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            System.err.println("Failed to load db.properties file. Exiting the application.");
            System.exit(2);
        }
    }

    public static String getURL() {
        return URL;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
