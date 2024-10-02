//package robsunApi.domain.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "posts")
//public class PostEntity extends BaseEntity {
//
//    private String title;
//    private String content;
//
//    @Column(name = "is_checked")
//    private Boolean isChecked = false;
//
//    // Constructors, getters, and setters
//    public PostEntity() {}
//
//    public PostEntity(String title, String content, Boolean isChecked) {
//        this.title = title;
//        this.content = content;
//        this.isChecked = isChecked;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Boolean getIsChecked() {
//        return isChecked;
//    }
//
//    public void setIsChecked(Boolean isChecked) {
//        this.isChecked = isChecked;
//    }
//}
