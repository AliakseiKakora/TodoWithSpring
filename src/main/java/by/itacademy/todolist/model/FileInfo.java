package by.itacademy.todolist.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id", "path", "directory", "name"})
@ToString(of = {"id", "path", "directory", "name"})

@Entity
@Table(name = "FILES_INFO")
public class FileInfo {
    @Id
    private Long id;

    private String path;

    private String directory;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Task task;

    public void addTask(Task task) {
        this.task = task;
        task.setFileInfo(this);
    }

    public void removeTask(Task task) {
        task.setFileInfo(null);
        this.task = null;
    }
}