package io.com.github.caualima17.todokio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseModelDTO {
    private Long id;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
