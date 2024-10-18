package robsunApi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class TokenEntity extends BaseEntity {

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expirationTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private int apiCalls;

    public TokenEntity() {
    }

    public String getToken() {
        return token;
    }

    public TokenEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getApiCalls() {
        return apiCalls;
    }

    public void setApiCalls(int apiCalls) {
        this.apiCalls = apiCalls;
    }

    public boolean isExpired() {
        return expirationTime != null && LocalDateTime.now().isAfter(expirationTime);
    }
}
