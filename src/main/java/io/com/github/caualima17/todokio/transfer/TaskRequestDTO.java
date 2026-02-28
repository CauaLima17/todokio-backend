package io.com.github.caualima17.todokio.transfer;

import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.model.TaskList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskRequestDTO {
    @NotNull @NotBlank
    private String name;
    private String description;
    private TaskList list;
    private List<TagRequestDTO> tags;
    private LocalDateTime dueTime;
    private List<Subtask> subtasks;

    public static Task fromDtoToEntity(TaskRequestDTO data) {
        List<Tag> tags = new ArrayList<>();

        if (Objects.nonNull(data.getTags())) {
            tags = data.getTags()
                    .stream()
                    .map(TagRequestDTO::fromDtoToEntity)
                    .toList();
        }

        return Task.builder()
                .name(data.getName())
                .description(data.getDescription())
                .list(data.getList())
                .tags(tags)
                .dueTime(data.getDueTime())
                .subtasks(data.getSubtasks())
                .build();
    }
}
