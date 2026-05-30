package io.com.github.caualima17.todokio.dto.subtask;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import io.com.github.caualima17.todokio.dto.task.TaskSimpleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubtaskResponseDTO extends BaseModelDTO {
    private String name;
    private TaskSimpleDTO task;
}
