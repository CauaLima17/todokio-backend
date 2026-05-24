package io.com.github.caualima17.todokio.dto.task;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import io.com.github.caualima17.todokio.dto.list.TaskListSimpleDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskSimpleDTO;
import io.com.github.caualima17.todokio.dto.tag.TagSimpleDTO;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
public class TaskResponseDTO extends BaseModelDTO {
    private String name;
    private String description;
    private TaskListSimpleDTO list;
    private List<TagSimpleDTO> tags;
    private LocalDateTime dueTime;
    private List<SubtaskSimpleDTO> subtasks;
}
