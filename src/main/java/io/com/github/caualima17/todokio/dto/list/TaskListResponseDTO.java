package io.com.github.caualima17.todokio.dto.list;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TaskListResponseDTO extends BaseModelDTO {
    private String name;
    private String description;
    private List<TaskSimpleDTO> tasks;
}
