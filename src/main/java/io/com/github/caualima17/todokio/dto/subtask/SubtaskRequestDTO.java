package io.com.github.caualima17.todokio.dto.subtask;

import io.com.github.caualima17.todokio.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubtaskRequestDTO {
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private Long taskID;
}
