package io.com.github.caualima17.todokio.infra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private ZonedDateTime timestamp;
    private int status;
    private String detail;
}
