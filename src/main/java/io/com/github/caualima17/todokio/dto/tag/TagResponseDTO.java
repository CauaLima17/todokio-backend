package io.com.github.caualima17.todokio.dto.tag;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TagResponseDTO extends BaseModelDTO {
    private String name;
    private List<TaskSimpleDTO> tasks;
}
