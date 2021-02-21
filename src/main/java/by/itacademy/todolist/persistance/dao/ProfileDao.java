package by.itacademy.todolist.persistance.dao;

public interface ProfileDao<Profile> extends CrudDao<Profile> {

    boolean existLoginAndEmail(String login, String email);

}
