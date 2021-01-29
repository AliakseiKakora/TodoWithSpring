package by.itacademy.todolist.persistance.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer<T> {

    void processCreateStatement(PreparedStatement pStatement, T t) throws SQLException;

    long processUpdateStatement(PreparedStatement pStatement, T t) throws SQLException;

}