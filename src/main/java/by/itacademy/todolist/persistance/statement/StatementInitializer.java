package by.itacademy.todolist.persistance.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer<T> {

    void processCreateStatement(PreparedStatement preparedStatement, T t) throws SQLException;

    long processUpdateStatement(PreparedStatement preparedStatement, T t) throws SQLException;

}