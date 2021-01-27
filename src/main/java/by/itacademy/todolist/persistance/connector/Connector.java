package by.itacademy.todolist.persistance.connector;

import java.sql.Connection;

public interface Connector {

    Connection getConnection();

}