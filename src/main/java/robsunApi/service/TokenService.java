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

    public void addGuestToken(String token) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(LocalDateTime.now());
        tokenEntity.setExpirationTime(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(tokenEntity);
    }

    public void addUserToken(TokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }

    public boolean isValidToken(String token) {
        Optional<TokenEntity> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isPresent()) {
            TokenEntity tokenEntity = tokenOptional.get();
            if (tokenEntity.getUser() != null && !tokenEntity.isExpired()) {
                tokenEntity.setApiCalls(tokenEntity.getApiCalls() + 1);
                tokenRepository.save(tokenEntity);
                return true;
            }
            return tokenEntity.getUser() == null && !tokenEntity.isExpired(); // Valid token without a user
        }
        return false;
    }

    public int getApiCallsForUser(String apiToken) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(apiToken);
        return tokenEntity.map(TokenEntity::getApiCalls).orElse(0);
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void cleanupExpiredTokens() {
        List<TokenEntity> expiredTokens = tokenRepository.findAllByUserIsNullAndExpirationTimeBefore(LocalDateTime.now());
        tokenRepository.deleteAll(expiredTokens); // Delete all expired guest tokens
    }
}
