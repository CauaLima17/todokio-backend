package io.com.github.caualima17.todokio.dto.subtask;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class SubtaskSimpleDTO extends BaseModelDTO {
    private String name;
}
