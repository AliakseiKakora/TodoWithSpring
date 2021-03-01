package by.itacademy.todolist.util;

import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.service.UserService;

public interface DependencyManager {

    UserService getUsersService();

    ProfileService getProfileService();

    TaskService getTaskService();

    FileService getFileService();

}
