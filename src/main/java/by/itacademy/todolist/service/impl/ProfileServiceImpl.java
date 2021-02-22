package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistance.dao.ProfileDao;
import by.itacademy.todolist.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {

    private ProfileDao<Profile> profileDao;

    public ProfileServiceImpl(ProfileDao<Profile> profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public boolean existLoginAndEmail(String login, String email) {
        return profileDao.existLoginAndEmail(login, email);
    }

    @Override
    public Profile update(Profile profile, String login, String password) {
        if (profile.getLogin().equals(login) && profile.getPassword().equals(password)) {
            return profile;
        }
        profile.setLogin(login);
        profile.setPassword(password);
        return profileDao.update(profile);
    }
}
