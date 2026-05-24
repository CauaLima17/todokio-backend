package io.com.github.caualima17.todokio.dto.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TagRequestDTO {
    @NotNull @NotBlank
    private String name;
    private List<Long> tasksID;
}
