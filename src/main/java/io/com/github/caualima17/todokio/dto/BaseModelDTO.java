package io.com.github.caualima17.todokio.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class BaseModelDTO {
    private Long id;
    private Date createdOn;
    private Date updatedOn;
}
