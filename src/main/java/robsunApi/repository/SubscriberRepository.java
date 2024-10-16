package robsunApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robsunApi.domain.entity.SubscriberEntity;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {
    Optional<SubscriberEntity> findByEmail(String subscriberEmail);

}
