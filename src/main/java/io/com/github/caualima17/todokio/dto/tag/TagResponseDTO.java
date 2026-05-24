package io.com.github.caualima17.todokio.dto.tag;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class TagResponseDTO extends BaseModelDTO {
    private String name;
    private List<TaskSimpleDTO> tasks;
}
