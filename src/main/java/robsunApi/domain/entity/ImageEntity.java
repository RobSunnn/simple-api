package robsunApi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    private String filePath;

    public ImageEntity() {
    }

    public ImageEntity(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
