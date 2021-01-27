package by.itacademy.todolist.persistance.query;

public interface CrudJdbcSqlQueryHolder {

    String getByIdSql();

    String getAllSql();

    String getUpdateSql();

    String getDeleteSql();

    String getCreateSql();
}