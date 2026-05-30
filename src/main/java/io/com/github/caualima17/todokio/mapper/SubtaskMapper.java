package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.subtask.SubtaskRequestDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskResponseDTO;
import io.com.github.caualima17.todokio.dto.subtask.SubtaskSimpleDTO;
import io.com.github.caualima17.todokio.model.Subtask;
import io.com.github.caualima17.todokio.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubtaskMapper {

    public SubtaskMapper() {
    }

    public Subtask toEntity(SubtaskRequestDTO data, Task task) {
        return Subtask.builder()
                .name(data.getName())
                .task(task)
                .build();
    }

    public Subtask toEntity(SubtaskRequestDTO data) {
        return Subtask.builder()
                .name(data.getName())
                .build();
    }

    public SubtaskResponseDTO toResponse(Subtask data) {
        return SubtaskResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .task(TaskMapper.toSimpleDTO(data.getTask()))
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
