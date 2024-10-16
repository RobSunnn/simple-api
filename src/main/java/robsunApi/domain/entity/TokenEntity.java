package robsunApi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tokens")
public class TokenEntity extends BaseEntity {

    private String token;

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
}
