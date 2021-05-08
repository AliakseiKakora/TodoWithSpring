package by.itacademy.todolist.util;

import by.itacademy.todolist.model.Task;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class TaskPredicateManagerImpl implements TaskPredicateManager {

    private final Map<String, Predicate<Task>> predicateMap = new HashMap<>();

    @PostConstruct
    public void init() {
        predicateMap.put("today", getTodayTaskPredicate());
        predicateMap.put("tomorrow", getTomorrowTaskPredicate() );
        predicateMap.put("someday", getSomeDayTaskPredicate());
        predicateMap.put("deleted", getDeletedTaskPredicate());
        predicateMap.put("fixed", getFixedTaskPredicate());
    }

    @Override
    public Predicate<Task> getPredicateBySection(String section) {
        return predicateMap.get(section);
    }

    private Predicate<Task> getTomorrowTaskPredicate() {
        return task -> isTomorrowTask(task) && !task.isCompleted() && !task.isDeleted();
    }

    private Predicate<Task> getTodayTaskPredicate() {
        return task -> isTodayOrBeforeTask(task) && !task.isCompleted() && !task.isDeleted();
    }

    private Predicate<Task> getSomeDayTaskPredicate() {
        return task -> isSomeDayTask(task) && !task.isCompleted() && !task.isDeleted();
    }

    private Predicate<Task> getDeletedTaskPredicate() {
        return Task::isDeleted;
    }

    private Predicate<Task> getFixedTaskPredicate() {
        return task -> !task.isDeleted() && task.isCompleted();
    }

    private boolean isTodayOrBeforeTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now();
        return (now.getYear() == dateCompletion.getYear()
                && now.getDayOfYear() == dateCompletion.getDayOfYear())
                || now.isAfter(dateCompletion);
    }

    private boolean isTomorrowTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        return now.getYear() == dateCompletion.getYear()
                && now.getDayOfYear() == dateCompletion.getDayOfYear();
    }

    private boolean isSomeDayTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        if (dateCompletion.getYear() > now.getYear()) {
            return true;
        }
        return dateCompletion.getYear() == now.getYear()
                && dateCompletion.getDayOfYear() > now.getDayOfYear();
    }
}