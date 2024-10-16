package robsunApi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class TokenEntity extends BaseEntity {

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expirationTime;
    private boolean isGuest;

    public TokenEntity(){

    }

    public TokenEntity(String token) {
        this.token = token;
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

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return isGuest && LocalDateTime.now().isAfter(expirationTime);
    }
}
