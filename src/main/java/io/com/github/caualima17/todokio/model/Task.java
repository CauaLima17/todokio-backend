package io.com.github.caualima17.todokio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "id_task_list")
    private TaskList list;
    @ManyToMany
    @JoinTable(
            name = "tasks_tags",
            joinColumns = @JoinColumn(name = "id_task"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(length = 580)
    private String description;
    private LocalDateTime dueTime;
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL
    )
    private List<Subtask> subtasks;
}
