package io.com.github.caualima17.todokio.dto.subtask;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubtaskSimpleDTO extends BaseModelDTO {
    private String name;
}
