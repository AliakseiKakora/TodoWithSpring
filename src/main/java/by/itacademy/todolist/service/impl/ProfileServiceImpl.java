package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistence.dao.ProfileDao;
import by.itacademy.todolist.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao<Profile> profileDao;

    public ProfileServiceImpl(ProfileDao<Profile> profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public boolean existLoginOrEmail(String login, String email) {
        return profileDao.existLoginOrEmail(login, email);
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
