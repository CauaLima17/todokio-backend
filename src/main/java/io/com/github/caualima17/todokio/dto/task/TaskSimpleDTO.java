package io.com.github.caualima17.todokio.dto.task;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public class TaskSimpleDTO extends BaseModelDTO {
    private String name;
    private String description;
    private LocalDateTime dueTime;
}
