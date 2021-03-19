package by.itacademy.todolist.persistence.dao;

public interface ProfileDao<Profile> extends CrudDao<Profile> {

    boolean existLoginOrEmail(String login, String email);

}
