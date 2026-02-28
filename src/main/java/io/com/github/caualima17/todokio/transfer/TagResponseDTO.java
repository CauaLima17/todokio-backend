package io.com.github.caualima17.todokio.transfer;

import io.com.github.caualima17.todokio.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TagResponseDTO {
    private Long id;
    private String name;
    private List<TaskResponseDTO> tasks;

    public static TagResponseDTO fromEntityToDto(Tag data) {
        return TagResponseDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .build();
    }
}
