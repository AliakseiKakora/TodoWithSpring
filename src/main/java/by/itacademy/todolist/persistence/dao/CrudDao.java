package by.itacademy.todolist.persistence.dao;

import java.util.List;

public interface CrudDao<T> {

    T getById(long id);

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteById(long id);

}