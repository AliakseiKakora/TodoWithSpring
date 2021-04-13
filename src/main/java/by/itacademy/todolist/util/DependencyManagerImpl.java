package by.itacademy.todolist.util;

import by.itacademy.todolist.model.*;
import by.itacademy.todolist.persistence.dao.*;
import by.itacademy.todolist.persistence.dao.impl.*;
import by.itacademy.todolist.service.*;
import by.itacademy.todolist.service.impl.*;

public class DependencyManagerImpl implements DependencyManager {

    private static final DependencyManager DEPENDENCY_MANAGER = new DependencyManagerImpl();

    private static final UserService USER_SERVICE;
    private static final ProfileService PROFILE_SERVICE;
    private static final TaskService TASK_SERVICE;
    private static final FileService FILE_SERVICE;
    private static final MessageService MESSAGE_SERVICE;
    private static final RoleService ROLE_SERVICE;


    static {
        ProfileDao<Profile> profileDao = new ProfileDaoJpa();
        RoleDao<Role> roleDao = new RoleDaoJpa();
        FileInfoDao<FileInfo> fileInfoDao = new FileInfoDaoJpa();
        TaskDao<Task> taskDao = new TaskDaoJpa();
        UserDao<User> userDao = new UserDaoJpa();
        MessageDao<Message> messageDao = new MessageDaoJpa();
        DateParser dateParser = new DateParserImpl();

        FILE_SERVICE = new FileServiceImpl(fileInfoDao);
        TASK_SERVICE = new TaskServiceImpl(taskDao, FILE_SERVICE, dateParser);
        ROLE_SERVICE = new RoleServiceImpl(roleDao);
        USER_SERVICE = new UserServiceImpl(userDao, ROLE_SERVICE, TASK_SERVICE);
        PROFILE_SERVICE = new ProfileServiceImpl(profileDao);
        MESSAGE_SERVICE = new MessageServiceImpl(messageDao);
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