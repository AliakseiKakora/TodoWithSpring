package by.itacademy.todolist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id", "name", "description", "dateAdded"})
@ToString(of = {"id", "name", "description", "dateAdded"})

@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @SequenceGenerator(name = "TASK_ID_SEQ_GEN", sequenceName ="TASK_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_ID_SEQ_GEN")
    private Long id;

    private String name;

    private String description;

    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now();

    @Column(name = "date_completion")
    private LocalDateTime dateCompletion;

    @Column(name = "completed")
    private boolean isCompleted;

    @Column(name = "deleted")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private FileInfo fileInfo;

    public void addFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        fileInfo.setTask(this);
    }

    public void removeFileInfo(FileInfo fileInfo) {
        this.fileInfo = null;
        fileInfo.setTask(null);
    }

    public void addUser(User user) {
        this.user = user;
        user.getTasks().add(this);
    }

    public void removeUser(User user) {
        user.getTasks().remove(this);
        this.user = null;
    }

}