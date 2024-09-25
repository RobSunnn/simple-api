package robsunApi.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {
    private final Set<String> validTokens = Collections.synchronizedSet(new HashSet<>());

    public void addToken(String token) {
        validTokens.add(token);
    }

    public void removeToken(String token) {
        validTokens.remove(token);
    }

    public boolean isValidToken(String token) {
        return validTokens.contains(token);
    }
}
