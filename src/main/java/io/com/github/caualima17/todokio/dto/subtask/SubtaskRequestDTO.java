package io.com.github.caualima17.todokio.dto.subtask;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubtaskRequestDTO {
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private Long taskID;
}
