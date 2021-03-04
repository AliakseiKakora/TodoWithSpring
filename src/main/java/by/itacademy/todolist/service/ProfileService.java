package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Profile;

public interface ProfileService {

    boolean existLoginAndEmail(String login, String email);

    Profile update(Profile profile);

}
