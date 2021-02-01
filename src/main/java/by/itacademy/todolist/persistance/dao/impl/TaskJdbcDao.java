package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.TaskDao;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.TaskResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.TaskSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.TaskStatementInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskJdbcDao extends AbstractJdbcDao<Task> implements TaskDao<Task> {

    private static final String CREATE_TASK_SQL = "insert into tasks (description, date_added, date_completion," +
            " completed, deleted, user_id) values (?,?,?,?,?,?)";
    private static final String GET_ALL_USER_TASK_SQL = "select t.id, t.description, t.date_added, t.date_completion, t.completed," +
            " t.deleted, fi.id as file_id, fi.path from tasks t left join files_info fi on fi.task_id = t.id where t.user_id = ?";

    private static final String DELETE_ALL_USER_TASKS_SQL = "delete from tasks where user_id = ?";

    private FileInfoJdbcDao fileInfoJdbcDao = new FileInfoJdbcDao();

    public TaskJdbcDao() {
        super(new TaskResultSetMapper(), new TaskSqlQueryHolder(), new TaskStatementInitializer());
    }

    @Override
    public Task createTaskForUser(Task task, long userId) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(CREATE_TASK_SQL, Statement.RETURN_GENERATED_KEYS)) {

            getStatementInitializer().processCreateStatement(pStatement, task);
            pStatement.setLong(6, userId);
            pStatement.executeUpdate();

            try (ResultSet resultSet = pStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return getById(resultSet.getLong(1));
                }
                throw new DaoException("Error generate ID for task : " + task);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error generate ID for task : " + task);
                throw new DaoException("Error generate ID for task : " + task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        Task task = getById(id);
        fileInfoJdbcDao.delete(task.getFileInfo().getId());
        super.delete(id);
    }

    @Override
    public List<Task> getAllUserTasks(long userId) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(GET_ALL_USER_TASK_SQL)) {

            pStatement.setLong(1, userId);

            try (ResultSet resultSet = pStatement.executeQuery()) {
                List<Task> tasks = new ArrayList<>();
                while (resultSet.next()) {
                    Task task = getResultSetMapper().processResultSet(resultSet);
                    tasks.add(task);
                }
                return tasks;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in method getAllUserTasks witch id - " + userId + " " + e.getMessage());
                throw new DaoException("Error in method getAllUserTasks witch id - " + userId + " " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }


    }

    @Override
    public void deleteAllUserTasks(long userId) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(DELETE_ALL_USER_TASKS_SQL)) {

            pStatement.setLong(1,userId);
            pStatement.executeUpdate();
            ////// delete this
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}