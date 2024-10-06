package robsunApi.domain.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterBM(
        @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
        @NotNull(message = "Username cannot be null")
        @NotBlank(message = "Username cannot be blank")
        String username,

        @Size(min = 5, max = 200, message = "Email must be between 5 and 200 characters")
        @NotNull(message = "Email cannot be null")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotNull(message = "Password cannot be null")
        @NotBlank(message = "Password cannot be blank")
        String password,

        @NotNull(message = "Confirm password cannot be null")
        @NotBlank(message = "Confirm password cannot be blank")
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
