package by.itacademy.todolist.util;

import by.itacademy.todolist.model.*;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.connector.impl.HikariConnector;
import by.itacademy.todolist.persistance.dao.*;
import by.itacademy.todolist.persistance.dao.impl.*;
import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;
import by.itacademy.todolist.service.impl.ProfileServiceImpl;
import by.itacademy.todolist.service.impl.UserServiceImpl;

public class DependencyManagerImpl implements DependencyManager {

    private static final DependencyManager DEPENDENCY_MANAGER = new DependencyManagerImpl();

    private static final UserService USER_SERVICE;
    private static final ProfileService PROFILE_SERVICE;


    static {
        Connector connector = HikariConnector.getInstance();
        ProfileDao<Profile> profileDao = new ProfileJdbcDao(connector);
        RoleDao<Role> roleDao = new RoleJdbcDao(connector);
        FileInfoDao<FileInfo> fileInfoDao = new FileInfoJdbcDao(connector);
        TaskDao<Task> taskDao = new TaskJdbcDao(connector, fileInfoDao);
        UserDao<User> userDao = new UserJdbcDao(connector, profileDao, roleDao, taskDao);

        USER_SERVICE = new UserServiceImpl(userDao);
        PROFILE_SERVICE = new ProfileServiceImpl(profileDao);
    }

    @Override
    public UserService getUsersService() {
        return USER_SERVICE;
    }

    @Override
    public ProfileService getProfileService() {
        return PROFILE_SERVICE;
    }

    public static DependencyManager getInstance() {
        return DEPENDENCY_MANAGER;
    }
}
