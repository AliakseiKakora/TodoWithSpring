package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaskStatementInitializer implements StatementInitializer<Task> {

    @Override
    public void processCreateStatement(PreparedStatement pStatement, Task task) throws SQLException {
        Timestamp dateAdded = Timestamp.valueOf(task.getDateAdded());
        Timestamp dateCompletion = Timestamp.valueOf(task.getDateCompletion());
        pStatement.setString(1, task.getDescription());
        pStatement.setTimestamp(2, dateAdded);
        pStatement.setTimestamp(3, dateCompletion);
        pStatement.setBoolean(4, task.isCompleted());
        pStatement.setBoolean(5, task.isDeleted());

    }

    @Override
    public long processUpdateStatement(PreparedStatement pStatement, Task task) throws SQLException {
        processCreateStatement(pStatement, task);
        pStatement.setLong(6, task.getId());
        return task.getId();
    }

}
