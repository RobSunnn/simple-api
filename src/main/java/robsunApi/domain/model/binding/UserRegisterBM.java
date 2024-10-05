package robsunApi.domain.model.binding;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterBM(
        @Size(min = 5, max = 20)
        @NotNull
        @NotBlank
        String username,
        @Size(min = 5, max = 200)
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String confirmPassword
) {
    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String confirmPassword() {
        return confirmPassword;
    }
}
