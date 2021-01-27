package by.itacademy.todolist.persistance.connector.impl;

import by.itacademy.todolist.persistance.connector.Connector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnector implements Connector {

    private static final String DATABASE_PROPERTIES_FILE_PATH = "/database/data-source.properties";

    private static HikariConnector hikariConnector;

    private final DataSource dataSource;

    private HikariConnector() {
        HikariConfig hikariConfig = new HikariConfig(DATABASE_PROPERTIES_FILE_PATH);
        dataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariConnector getInstance() {
        if (hikariConnector == null) {
            return hikariConnector = new HikariConnector();
        }
        return hikariConnector;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error create database connection - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error create database connection - " + e.getMessage());
        }
    }

}