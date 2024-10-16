package robsunApi.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import robsunApi.domain.entity.TokenEntity;
import robsunApi.domain.entity.UserEntity;
import robsunApi.domain.entity.enums.RoleEnum;
import robsunApi.domain.model.binding.UserRegisterBM;
import robsunApi.domain.model.view.UserView;
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
        String userToken = generateUserToken(); // Generate a user token
        user.setApiToken(userToken); // Set the generated token to the user entity

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


    public UserView findUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserEntity user = findUser(userEmail);

        return mapAsUserView(user);
    }

    private UserEntity mapToUser(UserRegisterBM userRegisterBM) {
        return new UserEntity()
                .setUsername(userRegisterBM.username())
                .setEmail(userRegisterBM.email())
                .setPassword(passwordEncoder.encode(userRegisterBM.password()))
                .setApiToken(UUID.randomUUID().toString())
                .setCreated(LocalDateTime.now());
    }

    private String generateUserToken() {
        String token = UUID.randomUUID().toString();
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(LocalDateTime.now());
        tokenEntity.setGuest(false);
        tokenService.addToken(tokenEntity);
        return token;
    }


    private UserView mapAsUserView(UserEntity user) {
        return new UserView(user.getUsername(), user.getEmail(), user.getApiToken());
    }

    private UserEntity findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
    }
}
