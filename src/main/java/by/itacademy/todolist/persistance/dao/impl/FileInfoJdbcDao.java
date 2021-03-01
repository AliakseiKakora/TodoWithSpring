package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.FileInfoDao;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.FileInfoResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.FileInfoSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.FileInfoStatementInitializer;

import java.sql.*;

public class FileInfoJdbcDao extends AbstractJdbcDao<FileInfo> implements FileInfoDao<FileInfo> {

    private static final String ADD_FILE_FOR_TASK_SQL = "insert into files_info (name, directory, path, task_id) values (?,?,?,?)";

    public FileInfoJdbcDao(Connector connector) {
        super(connector, new FileInfoResultSetMapper(), new FileInfoSqlQueryHolder(), new FileInfoStatementInitializer());
    }

    @Override
    public FileInfo addFileInfoForTask(FileInfo fileInfo, long task_id) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(ADD_FILE_FOR_TASK_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pStatement.setString(1, fileInfo.getName());
            pStatement.setString(2, fileInfo.getDirectory());
            pStatement.setString(3, fileInfo.getPath());
            pStatement.setLong(4, task_id);
            pStatement.executeUpdate();

            try (ResultSet resultSet = pStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return getById(resultSet.getLong(1));
                }
                throw new DaoException("Error generate ID for FileInfo : " + fileInfo);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error generate ID for FileInfo : " + fileInfo);
                throw new DaoException("Error generate ID for FileInfo : " + fileInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

}