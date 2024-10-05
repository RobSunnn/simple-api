package robsunApi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import robsunApi.domain.entity.UserEntity;
import robsunApi.domain.entity.enums.RoleEnum;
import robsunApi.domain.model.binding.UserRegisterBM;
import robsunApi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, TokenService tokenService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRegisterBM userRegisterBM) {
        UserEntity user = mapToUser(userRegisterBM);
        tokenService.addToken(user.getApiToken());

        if (roleService.getCount() == 0) {
            roleService.initRoles();
        }
        if (userRepository.count() == 0) {
            user.setRoles(roleService.getAllRoles());
        } else {
            user.setRoles(roleService.getAllRoles().stream().filter(r -> r.getName().equals(RoleEnum.USER)).toList());
        }

        userRepository.save(user);
    }

    private UserEntity mapToUser(UserRegisterBM userRegisterBM) {
        return new UserEntity()
                .setUsername(userRegisterBM.username())
                .setEmail(userRegisterBM.email())
                .setPassword(passwordEncoder.encode(userRegisterBM.password()))
                .setApiToken(UUID.randomUUID().toString())
                .setCreated(LocalDateTime.now());
    }

}
