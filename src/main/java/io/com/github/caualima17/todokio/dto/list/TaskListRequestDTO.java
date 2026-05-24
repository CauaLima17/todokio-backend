package io.com.github.caualima17.todokio.dto.list;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TaskListRequestDTO {
    @NotBlank @NotNull
    private String name;
    private String description;
    private List<Long> taskID;
}
