package robsunApi.domain.model.binding;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class PostBindingModel {

    @NotBlank
    @Size(min = 2, max = 200)
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Title must only contain letters, numbers, and spaces.")
    private String title;
    @NotBlank
    @Lob
    @Size(min = 2)
    private String content;
    @NotNull
    private MultipartFile file;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
