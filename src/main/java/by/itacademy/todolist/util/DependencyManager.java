package by.itacademy.todolist.util;

import by.itacademy.todolist.service.*;

public interface DependencyManager {

    UserService getUsersService();

    ProfileService getProfileService();

    TaskService getTaskService();

    FileService getFileService();

    MessageService getMessageService();

}
