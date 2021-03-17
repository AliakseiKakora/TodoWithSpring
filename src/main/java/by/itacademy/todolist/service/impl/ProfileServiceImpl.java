package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistance.dao.ProfileDao;
import by.itacademy.todolist.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao<Profile> profileDao;

    public ProfileServiceImpl(ProfileDao<Profile> profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public boolean existLoginAndEmail(String login, String email) {
        return profileDao.existLoginAndEmail(login, email);
    }

    @Override
    public Profile update(Profile profile) {
        if (profile.getPassword() == null
                || profile.getLogin() == null
                || profile.getLogin().equals("")
                || profile.getPassword().equals("")) {

            throw new RuntimeException("Password or login cannot be empty");

        }
        return profileDao.update(profile);
    }
}
