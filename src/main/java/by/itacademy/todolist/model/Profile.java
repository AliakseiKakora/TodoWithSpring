package by.itacademy.todolist.model;


import by.itacademy.todolist.validation.ValidPassword;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id", "login", "password"})
@ToString(of = {"id", "login"})

@Entity
@Table(name = "PROFILES")
public class Profile {
    @Id
    private Long id;

    @NotEmpty(message = "{validation.NotEmpty.message}")
    private String login;

    @ValidPassword
    private String password;

    @Column(name = "is_enable")
    private boolean isEnable = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public void addUser(User user) {
        this.user = user;
        user.setProfile(this);
    }

    public void removeUser() {
        user.setProfile(null);
        user= null;
    }
}