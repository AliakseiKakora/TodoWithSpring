package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Profile;

public interface ProfileService {

    boolean existLoginOrEmail(String login, String email);

    Profile update(Profile profile);

    void updatePasswordByLogin(String password, String login);

}