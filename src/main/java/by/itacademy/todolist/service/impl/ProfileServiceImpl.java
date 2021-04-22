package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistence.ProfileRepository;
import by.itacademy.todolist.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public boolean existLoginOrEmail(String login, String email) {
        return profileRepository.existsByLoginOrUserEmailIgnoreCase(login, email);
    }

    @Override
    public Profile update(Profile profile) {
        if (profile.getPassword() == null
                || profile.getLogin() == null
                || profile.getLogin().equals("")
                || profile.getPassword().equals("")) {

            throw new RuntimeException("Password or login cannot be empty");

        }
        return profileRepository.save(profile);
    }
}