package robsunApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import robsunApi.domain.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
