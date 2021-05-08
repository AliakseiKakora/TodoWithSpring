package by.itacademy.todolist.util;

import by.itacademy.todolist.model.Task;

import java.util.function.Predicate;

public interface TaskPredicateManager {

    Predicate<Task> getPredicateBySection(String section);

}
