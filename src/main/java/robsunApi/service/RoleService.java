package robsunApi.service;

import org.springframework.stereotype.Service;
import robsunApi.domain.entity.RoleEntity;
import robsunApi.domain.entity.enums.RoleEnum;
import robsunApi.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void initRoles() {
        roleRepository.save(new RoleEntity(RoleEnum.ADMIN));
        roleRepository.save(new RoleEntity(RoleEnum.MODERATOR));
        roleRepository.save(new RoleEntity(RoleEnum.USER));
    }

    public long getCount() {
        return roleRepository.count();
    }

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

}
