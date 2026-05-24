package io.com.github.caualima17.todokio.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class TaskRequestDTO {
    @NotNull @NotBlank
    private String name;
    private String description;
    private LocalDateTime dueTime;
    private Long listID;
    private List<Long> tagsID;
    private List<Long> subtasksID;
}
