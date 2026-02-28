package io.com.github.caualima17.todokio.transfer;

import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.model.TaskList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskResponseDTO {
    private Long id;
    private String name;
    private String description;
    private TaskList list;
    private List<TagResponseDTO> tags;
    private LocalDateTime dueTime;
    private List<Subtask> subtasks;

    public static TaskResponseDTO fromEntityToDTO(Task task) {
        List<TagResponseDTO> tags = task.getTags()
                .stream()
                .map(TagResponseDTO::fromEntityToDto)
                .toList();

        return TaskResponseDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .list(task.getList())
                .tags(tags)
                .dueTime(task.getDueTime())
                .subtasks(task.getSubtasks())
                .build();
    }
}
