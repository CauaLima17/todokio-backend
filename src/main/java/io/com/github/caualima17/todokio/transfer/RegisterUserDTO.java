package io.com.github.caualima17.todokio.transfer;

import io.com.github.caualima17.todokio.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterUserDTO {
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String password;

    public User fromDtoToEntity() {
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        String encryptedPassword = crypt.encode(this.password);

        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(encryptedPassword)
                .build();
    }
}
