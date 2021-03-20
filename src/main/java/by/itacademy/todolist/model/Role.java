package by.itacademy.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "role")
@ToString(of = "role")

@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @SequenceGenerator(name = "ROLE_ID_SEQ_GEN", sequenceName = "ROLE_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_ID_SEQ_GEN")
    private Long id;

    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ROLE_USER",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> users = new HashSet<>();

    public Role(String role) {
        this.role = role;
    }

    public void addUser(User user) {
        user.getRoles().add(this);
        users.add(user);
    }

    public void removeUser(User user) {
        user.getRoles().remove(this);
        users.remove(user);
    }
}