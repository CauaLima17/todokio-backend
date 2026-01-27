package io.com.github.caualima17.todokio.transfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationTokenDTO {
    private Long id;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String token;
}
