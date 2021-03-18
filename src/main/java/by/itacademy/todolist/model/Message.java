package by.itacademy.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id", "message", "dateAdded"})
@ToString(of = {"id", "message", "dateAdded"})

@Entity
@Table(name = "MESSAGES")
public class Message {
    @Id
    @SequenceGenerator(name = "MESSAGE_ID_SEQ_GEN", sequenceName ="MESSAGE_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQ_GEN")
    private Long id;

    private String message;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void addUser(User user) {
        this.user = user;
        user.getMessages().add(this);
    }

    public void removeUser(User user) {
        this.user = null;
        user.getMessages().remove(this);
    }
}