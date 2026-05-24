package io.com.github.caualima17.todokio.dto.tag;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class TagSimpleDTO extends BaseModelDTO {
    private String name;
}
