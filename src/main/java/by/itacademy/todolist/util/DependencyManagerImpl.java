package by.itacademy.todolist.util;

import by.itacademy.todolist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependencyManagerImpl implements DependencyManager {

//    public static final org.springframework.context.ApplicationContext APPLICATION_CONTEXT =
//            new AnnotationConfigApplicationContext(ApplicationConfig.class);
//
//    @Autowired
//    private static DependencyManager DEPENDENCY_MANAGER= APPLICATION_CONTEXT.getBean(DependencyManager.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FileService fileService;
    @Autowired
    private MessageService messageService;


    static {
//        ProfileDao<Profile> profileDao = new ProfileDaoJpa();
//        RoleDao<Role> roleDao = new RoleDaoJpa();
//        FileInfoDao<FileInfo> fileInfoDao = new FileInfoDaoJpa();
//        TaskDao<Task> taskDao = new TaskDaoJpa();
//        UserDao<User> userDao = new UserDaoJpa();
//        MessageDao<Message> messageDao = new MessageDaoJpa();
//        DateParser dateParser = new DateParserImpl();
//
//        FILE_SERVICE = new FileServiceImpl(fileInfoDao);
//        TASK_SERVICE = new TaskServiceImpl(taskDao, FILE_SERVICE, dateParser);
//        ROLE_SERVICE = new RoleServiceImpl(roleDao);
//        USER_SERVICE = new UserServiceImpl(userDao, ROLE_SERVICE, TASK_SERVICE);
//        PROFILE_SERVICE = new ProfileServiceImpl(profileDao);
//        MESSAGE_SERVICE = new MessageServiceImpl(messageDao);
    }

    @Override
    public UserService getUsersService() {
        return userService;
    }

    @Override
    public ProfileService getProfileService() {
        return profileService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public FileService getFileService() {
        return fileService;
    }

    @Override
    public MessageService getMessageService() {
        return messageService;
    }

//    public static DependencyManager getInstance() {
//        return DEPENDENCY_MANAGER;
//    }
}