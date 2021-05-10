package by.itacademy.todolist.security;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsParser userDetailsParser;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByProfileLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userDetailsParser.parseUserToUserDetails(user);
    }
}