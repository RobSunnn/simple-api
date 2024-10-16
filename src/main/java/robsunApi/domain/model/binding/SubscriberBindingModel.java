package robsunApi.domain.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SubscriberBindingModel(
        @NotNull(message = "Name cannot be null.")
        @NotBlank(message = "Email cannot be blank.")
        @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Invalid email address.")
        String subscriberEmail
) {
}
