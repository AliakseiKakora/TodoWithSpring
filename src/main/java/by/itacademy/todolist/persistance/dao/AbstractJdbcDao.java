package by.itacademy.todolist.persistance.dao;

import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;
import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.StatementInitializer;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractJdbcDao<T> implements CrudDao<T> {

    private final Connector connector;
    private final ResultSetMapper<T> resultSetMapper;
    private final CrudJdbcSqlQueryHolder queryHolder;
    private final StatementInitializer<T> statementInitializer;

    public AbstractJdbcDao(Connector connector, ResultSetMapper<T> resultSetMapper, CrudJdbcSqlQueryHolder queryHolder,
                           StatementInitializer<T> statementInitializer) {
        this.connector = connector;
        this.resultSetMapper = resultSetMapper;
        this.queryHolder = queryHolder;
        this.statementInitializer = statementInitializer;
    }

    @Override
    public T getById(long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(getQueryHolder().getByIdSql())) {

            pStatement.setLong(1, id);
            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getResultSetMapper().processResultSet(resultSet);
                }
                throw new DaoException("Invalid entity id: " + id);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in method getById " + e.getMessage());
                throw new DaoException("Error in method getById " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(getQueryHolder().getAllSql())) {

            try (ResultSet resultSet = pStatement.executeQuery()) {
                List<T> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(getResultSetMapper().processResultSet(resultSet));
                }
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in method getAll " + e.getMessage());
                throw new DaoException("Error in method getAll " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

    @Override
    public T create(T t) {
        try (Connection connection = connector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(getQueryHolder().getCreateSql(), Statement.RETURN_GENERATED_KEYS)) {

            getStatementInitializer().processCreateStatement(pStatement, t);
            pStatement.executeUpdate();

            try (ResultSet resultSet = pStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return getById(resultSet.getLong(1));
                }
                throw new DaoException("Error generate ID for create entity: " + t);

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error generate ID for create entity: " + t);
                throw new DaoException("Error generate ID for create entity: " + t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

    @Override
    public T update(T t) {
        try (Connection connection = connector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(getQueryHolder().getUpdateSql())) {

            long id = getStatementInitializer().processUpdateStatement(pStatement, t);
            pStatement.executeUpdate();

            return getById(id);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in method update - : " + t + ". " + e.getMessage());
            throw new DaoException("Error in method update - : " + t + ". " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(getQueryHolder().getDeleteSql())) {

            pStatement.setLong(1, id);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in method delete witch id - " + id + ". " + e.getMessage());
            throw new DaoException("Error in method delete witch id - " + id + ". " + e.getMessage());
        }
    }

}