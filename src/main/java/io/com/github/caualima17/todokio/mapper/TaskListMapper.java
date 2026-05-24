package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.list.TaskListRequestDTO;
import io.com.github.caualima17.todokio.dto.list.TaskListResponseDTO;
import io.com.github.caualima17.todokio.dto.list.TaskListSimpleDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskResponseDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskSimpleDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.TaskList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskListMapper {

    public TaskList fromDtoToEntity(TaskListRequestDTO data) {
        return TaskList.builder()
                .name(data.getName())
                .description(data.getDescription())
                .build();
    }

    public TaskListResponseDTO fromEntityToDto(TaskList data) {
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

    public TaskListSimpleDTO fromEntityToSimpleDto(TaskList data) {
        return TaskListSimpleDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .description(data.getDescription())
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }
}
