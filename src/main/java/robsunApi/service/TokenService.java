package robsunApi.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import robsunApi.domain.entity.TokenEntity;
import robsunApi.repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void addToken(String token) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(LocalDateTime.now());
        tokenEntity.setExpirationTime(LocalDateTime.now().plusMinutes(30));
        tokenEntity.setGuest(true);
        tokenRepository.save(tokenEntity);
    }

    public void addToken(TokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }

    public boolean isValidToken(String token) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(token);
        return tokenEntity.isPresent() && (!tokenEntity.get().isGuest() || !tokenEntity.get().isExpired());
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void cleanupExpiredTokens() {
        List<TokenEntity> expiredTokens = tokenRepository.findAllByIsGuestTrueAndExpirationTimeBefore(LocalDateTime.now());
        tokenRepository.deleteAll(expiredTokens); // Delete all expired guest tokens
    }
}
