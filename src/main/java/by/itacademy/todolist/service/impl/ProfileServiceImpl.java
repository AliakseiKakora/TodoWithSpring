package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistence.ProfileRepository;
import by.itacademy.todolist.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public void updatePasswordByLogin(String password, String login) {
        String encodedPassword = passwordEncoder.encode(password);
        profileRepository.updatePasswordByLogin(encodedPassword, login);
    }
}