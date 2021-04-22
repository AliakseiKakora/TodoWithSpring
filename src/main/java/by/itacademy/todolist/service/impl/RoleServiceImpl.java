package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistence.RoleRepository;
import by.itacademy.todolist.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getByNameWithUsers(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public void deleteAllUserRoles(long userId) {
        roleRepository.deleteAllUserRoles(userId);
    }
}