package io.com.github.caualima17.todokio.dto.subtask;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubtaskSimpleDTO extends BaseModelDTO {
    private String name;
}
