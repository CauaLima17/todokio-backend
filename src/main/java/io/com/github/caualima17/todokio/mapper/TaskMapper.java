package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.task.TaskRequestDTO;
import io.com.github.caualima17.todokio.dto.task.TaskResponseDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.model.TaskList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskMapper() {
    }

    public Task toEntity(TaskRequestDTO data) {
        return Task.builder()
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .build();
    }

    public Task toEntity(TaskRequestDTO data, TaskList taskList, List<Tag> tags, List<Subtask> subtasks) {
        return Task.builder()
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .list(taskList)
                .tags(tags)
                .subtasks(subtasks)
                .build();
    }

    public TaskResponseDTO toResponse(Task data) {
        return TaskResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .list(TaskListMapper.toSimpleDTO(data.getList()))
                .tags(TagMapper.toSimpleDTO(data.getTags()))
                .subtasks(SubtaskMapper.fromEntityToSimpleDto(data.getSubtasks()))
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public TaskSimpleDTO toSimpleDTO(Task data) {
        if (data == null) return null;

        return TaskSimpleDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .dueTime(data.getDueTime())
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public List<TaskSimpleDTO> toSimpleDTO(List<Task> tasks) {
        List<TaskSimpleDTO> simpleDTOs = new ArrayList<>();

        for (Task data : tasks) {
            simpleDTOs.add(
                    TaskSimpleDTO.builder()
                            .id(data.getId())
                            .name(data.getName())
                            .description(data.getDescription())
                            .dueTime(data.getDueTime())
                            .createdOn(data.getCreationDate())
                            .updatedOn(data.getUpdateDate())
                            .build()
            );
        }

        return simpleDTOs;
    }
}
