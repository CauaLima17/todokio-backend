package io.com.github.caualima17.todokio.mapper;

import io.com.github.caualima17.todokio.dto.tag.TagRequestDTO;
import io.com.github.caualima17.todokio.dto.tag.TagResponseDTO;
import io.com.github.caualima17.todokio.dto.tag.TagSimpleDTO;
import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {

    public TagMapper() {
    }

    public Tag toEntity(TagRequestDTO data) {
        return Tag.builder()
                .name(data.getName())
                .build();
    }

    public Tag toEntity(TagRequestDTO data, List<Task> tasks) {
        return Tag.builder()
                .name(data.getName())
                .tasks(tasks)
                .build();
    }

    public TagResponseDTO toResponse(Tag data) {
        return TagResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .tasks(TaskMapper.toSimpleDTO(data.getTasks()))
                .createdOn(data.getCreationDate())
                .updatedOn(data.getUpdateDate())
                .build();
    }

    public static TagSimpleDTO toSimpleDTO(Tag data) {
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
