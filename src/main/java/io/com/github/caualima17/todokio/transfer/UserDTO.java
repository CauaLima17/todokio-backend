package io.com.github.caualima17.todokio.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.com.github.caualima17.todokio.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public static UserDTO fromEntityToDTO(User data) {
        return UserDTO.builder()
                .id(data.getId())
                .name(data.getName())
                .email(data.getEmail())
                .build();
    }
}
