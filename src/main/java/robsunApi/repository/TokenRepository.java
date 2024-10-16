package robsunApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robsunApi.domain.entity.TokenEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);
    List<TokenEntity> findAllByIsGuestTrueAndExpirationTimeBefore(LocalDateTime now);
}
