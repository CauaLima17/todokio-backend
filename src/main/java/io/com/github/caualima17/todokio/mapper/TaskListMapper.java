package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.list.TaskListRequestDTO;
import io.com.github.caualima17.todokio.dto.list.TaskListResponseDTO;
import io.com.github.caualima17.todokio.dto.list.TaskListSimpleDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import io.com.github.caualima17.todokio.model.TaskList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskListMapper {

    public TaskList toEntity(TaskListRequestDTO data) {
        return TaskList.builder()
                .name(data.getName())
                .description(data.getDescription())
                .build();
    }

    public TaskListResponseDTO toResponse(TaskList data) {
        List<TaskSimpleDTO> tasks = new ArrayList<>();

        return TaskListResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .tasks(tasks)
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public static TaskListSimpleDTO toSimpleDTO(TaskList data) {
        if (data == null) return null;

        return TaskListSimpleDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }
}
