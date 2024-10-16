package robsunApi.service;

import org.springframework.stereotype.Service;
import robsunApi.domain.entity.TokenEntity;
import robsunApi.repository.TokenRepository;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void addToken(String token) {
        TokenEntity tokenEntity = new TokenEntity(token);
        tokenRepository.save(tokenEntity);
    }

    public void removeToken(String token) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(token);
        tokenEntity.ifPresent(tokenRepository::delete);
    }

    public boolean isValidToken(String token) {
        return tokenRepository.findByToken(token).isPresent();
    }
}
