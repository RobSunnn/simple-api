package robsunApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robsunApi.domain.entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
