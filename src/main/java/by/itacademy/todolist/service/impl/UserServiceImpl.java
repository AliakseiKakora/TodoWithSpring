package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.UserRepository;
import by.itacademy.todolist.service.RoleService;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        user.getProfile().setPassword(passwordEncoder.encode(user.getProfile().getPassword()));
        Role userRole = roleService.getByNameWithUsers(ApplicationConstants.ROLE_USER_VALUE);
        user = userRepository.save(user);
        user.addRole(userRole);

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user with id " + id + " not found"));
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByProfileLogin(login)
                .orElseThrow(() -> new RuntimeException("user with login " + login + " not found"));
    }

    @Override
    public void deleteById(long id) {
        taskService.deleteAllUserTasks(id);
        roleService.deleteAllUserRoles(id);
        userRepository.deleteById(id);
    }
}