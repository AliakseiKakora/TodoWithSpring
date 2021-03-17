package by.itacademy.todolist.util;

import by.itacademy.todolist.model.*;

import by.itacademy.todolist.persistance.dao.*;
import by.itacademy.todolist.service.*;
import by.itacademy.todolist.service.impl.*;
import com.sun.jdi.connect.Connector;

public class DependencyManagerImpl implements DependencyManager {

    private static final DependencyManager DEPENDENCY_MANAGER = new DependencyManagerImpl();

    private static final UserService USER_SERVICE;
    private static final ProfileService PROFILE_SERVICE;
    private static final TaskService TASK_SERVICE;
    private static final FileService FILE_SERVICE;
    private static final MessageService MESSAGE_SERVICE;


    static {
      //  Connector connector = HikariConnector.getInstance();
//        ProfileDao<Profile> profileDao = new ProfileJdbcDao(connector);
//        RoleDao<Role> roleDao = new RoleJdbcDao(connector);
//        FileInfoDao<FileInfo> fileInfoDao = new FileInfoJdbcDao(connector);
//        TaskDao<Task> taskDao = new TaskJdbcDao(connector);
//        UserDao<User> userDao = new UserJdbcDao(connector, profileDao, roleDao, taskDao);
//        MessageDao<Message> messageDao = new MessageJdbcDao(connector);
//
//        USER_SERVICE = new UserServiceImpl(userDao);
//        PROFILE_SERVICE = new ProfileServiceImpl(profileDao);
//        FILE_SERVICE = new FileServiceImpl(fileInfoDao);
//        TASK_SERVICE = new TaskServiceImpl(taskDao, FILE_SERVICE);
//        MESSAGE_SERVICE = new MessageServiceImpl(messageDao);


        USER_SERVICE = null;
        PROFILE_SERVICE = null;
        FILE_SERVICE = null;
        TASK_SERVICE = null;
        MESSAGE_SERVICE = null;
    }

    @Override
    public UserService getUsersService() {
        return USER_SERVICE;
    }

    @Override
    public ProfileService getProfileService() {
        return PROFILE_SERVICE;
    }

    @Override
    public TaskService getTaskService() {
        return TASK_SERVICE;
    }

    @Override
    public FileService getFileService() {
        return FILE_SERVICE;
    }

    @Override
    public MessageService getMessageService() {
        return MESSAGE_SERVICE;
    }

    public static DependencyManager getInstance() {
        return DEPENDENCY_MANAGER;
    }
}