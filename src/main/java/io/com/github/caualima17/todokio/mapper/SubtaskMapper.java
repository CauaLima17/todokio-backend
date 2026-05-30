package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.subtask.SubtaskRequestDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskResponseDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskSimpleDTO;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubtaskMapper {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public SubtaskMapper(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Subtask toEntity(SubtaskRequestDTO data) {
        Task task = taskRepository.findById(data.getTaskID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A tarefa a qual essa subtarefa está associada não existe."));

        return Subtask.builder()
                .name(data.getName())
                .task(task)
                .build();
    }

    public SubtaskResponseDTO toResponse(Subtask data) {
        return SubtaskResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .task(taskMapper.toSimpleDTO(data.getTask()))
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public SubtaskSimpleDTO toSimpleDTO(Subtask data) {
        if (data == null) return null;

        return SubtaskSimpleDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public static List<SubtaskSimpleDTO> toSimpleDTO(List<Subtask> subtasks) {
        List<SubtaskSimpleDTO> simpleDTOs = new ArrayList<>();

        for (Subtask data : subtasks) {
            simpleDTOs.add(
                    SubtaskSimpleDTO.builder()
                            .id(data.getId())
                            .name(data.getName())
                            .createdOn(data.getCreationDate())
                            .updatedOn(data.getUpdateDate())
                            .build()
            );
        }

        return simpleDTOs;
    }
}
