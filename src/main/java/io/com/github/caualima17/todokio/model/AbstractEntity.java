package io.com.github.caualima17.todokio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private boolean isRemoved;

    @PrePersist
    public void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    @PreRemove
    public void onDelete() {
        this.deleteDate = LocalDateTime.now();
        this.isRemoved = true;
    }
}
