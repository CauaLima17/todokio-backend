package io.com.github.caualima17.todokio.dto.list;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class TaskListSimpleDTO extends BaseModelDTO {
    private String name;
    private String description;
}
