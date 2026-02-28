package io.com.github.caualima17.todokio.transfer;

import io.com.github.caualima17.todokio.model.Tag;
import io.com.github.caualima17.todokio.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TagRequestDTO {
    @NotBlank @NotNull
    private String name;
    private List<TaskRequestDTO> tasks;

    public static Tag fromDtoToEntity(TagRequestDTO data) {
        List<Task> tasks = new ArrayList<>();

        if (Objects.nonNull(data.getTasks())) {
            tasks = data.getTasks()
                    .stream()
                    .map(TaskRequestDTO::fromDtoToEntity)
                    .toList();
        }

        return Tag.builder()
                .name(data.getName())
                .tasks(tasks)
                .build();
    }
}
