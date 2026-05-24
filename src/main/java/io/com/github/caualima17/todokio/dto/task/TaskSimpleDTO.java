package io.com.github.caualima17.todokio.dto.task;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TaskSimpleDTO extends BaseModelDTO {
    private String name;
    private String description;
    private LocalDateTime dueTime;
}
