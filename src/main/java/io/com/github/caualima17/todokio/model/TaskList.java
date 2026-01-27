package io.com.github.caualima17.todokio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskList extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(length = 580)
    private String description;
}
