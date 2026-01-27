package io.com.github.caualima17.todokio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subtask extends AbstractEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne()
    @JoinColumn(name = "id_task", nullable = false)
    private Task task;
}
