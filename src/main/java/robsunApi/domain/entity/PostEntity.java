package robsunApi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity {
    private String title;
    @Lob
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;


    public PostEntity() {}

    public PostEntity(String title, String content, ImageEntity image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public PostEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public ImageEntity getImage() {
        return image;
    }

    public PostEntity setImage(ImageEntity image) {
        this.image = image;
        return this;
    }
}
