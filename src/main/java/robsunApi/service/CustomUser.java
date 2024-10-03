package robsunApi.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private String username;

    public CustomUser(
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String username) {
        super(email, password, authorities);
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
