package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.tag.TagRequestDTO;
import io.com.github.caualima17.todokio.dto.tag.TagResponseDTO;
import io.com.github.caualima17.todokio.dto.tag.TagSimpleDTO;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import io.com.github.caualima17.todokio.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TagMapper(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Tag toEntity(TagRequestDTO data) {
        List<Task> tasks = taskRepository.findAllById(data.getTasksID());

        return Tag.builder()
                .name(data.getName())
                .tasks(tasks)
                .build();
    }

    public TagResponseDTO toResponse(Tag data) {
        return TagResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .tasks(taskMapper.toSimpleDTO(data.getTasks()))
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public TagSimpleDTO toSimpleDTO(Tag data) {
        if (data == null) return null;

        return TagSimpleDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public static List<TagSimpleDTO> toSimpleDTO(List<Tag> tags) {
        List<TagSimpleDTO> simpleDTOS = new ArrayList<>();

        for (Tag data : tags) {
            simpleDTOS.add(
                    TagSimpleDTO.builder()
                    .id(data.getId())
                    .name(data.getName())
                    .createdOn(data.getCreationDate())
                    .updatedOn(data.getUpdateDate())
                    .build()
            );
        }

        return simpleDTOS;
    }
}
