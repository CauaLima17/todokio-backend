package io.com.github.caualima17.todokio.dto.tag;

import io.com.github.caualima17.todokio.dto.BaseModelDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TagSimpleDTO extends BaseModelDTO {
    private String name;
}
