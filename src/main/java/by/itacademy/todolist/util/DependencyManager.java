package by.itacademy.todolist.util;

import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;

public interface DependencyManager {

    UserService getUsersService();

    ProfileService getProfileService();

}
