package by.itacademy.todolist.security;

import by.itacademy.todolist.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDetailsParser {

    public UserDetailsImpl parseUserToUserDetails(User user) {
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());

        return new UserDetailsImpl(user.getProfile().getLogin(),
                user.getProfile().getPassword(), user.getProfile().isEnable(), user.getProfile().isEnable(),
                user.getProfile().isEnable(), user.getProfile().isEnable(), grantedAuthorities, user.getId());
    }

}
