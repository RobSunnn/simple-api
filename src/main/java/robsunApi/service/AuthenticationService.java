package robsunApi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import robsunApi.domain.entity.TokenEntity;
import robsunApi.domain.entity.UserEntity;
import robsunApi.domain.entity.enums.RoleEnum;
import robsunApi.domain.model.binding.UserRegisterBM;
import robsunApi.domain.model.view.UserView;
import robsunApi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

    public ResponseEntity<?> registerUser(UserRegisterBM userRegisterBM, BindingResult bindingResult) {

        if (!userRegisterBM.password().equals(userRegisterBM.confirmPassword())) {
            bindingResult.addError(new FieldError("PasswordMismatch", "confirmPassword", "Passwords do not match!"));
        }

        if (userRepository.findByEmail(userRegisterBM.email()).isPresent()) {
            bindingResult.addError(new FieldError("EmailExists", "email", "Email is occupied!"));
        }

        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("errors", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(errors);
        }

        UserEntity user = mapToUser(userRegisterBM);

        if (roleService.getCount() == 0) {
            roleService.initRoles();
        }
        if (userRepository.count() == 0) {
            user.setRoles(roleService.getAllRoles());
        } else {
            user.setRoles(roleService.getAllRoles().stream().filter(r -> r.getName().equals(RoleEnum.USER)).toList());
        }

        userRepository.save(user);
        tokenService.addUserToken(user.getToken());

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }


    public UserView findUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        UserEntity user = findUser(userEmail);

        return mapAsUserView(user);
    }

    private UserEntity mapToUser(UserRegisterBM userRegisterBM) {
        TokenEntity userToken = generateUserToken();

        UserEntity userEntity = new UserEntity()
                .setUsername(userRegisterBM.username())
                .setEmail(userRegisterBM.email())
                .setPassword(passwordEncoder.encode(userRegisterBM.password()))
                .setToken(userToken)
                .setCreated(LocalDateTime.now());

        userToken.setUser(userEntity);

        return userEntity;
    }

    private TokenEntity generateUserToken() {
        String token = UUID.randomUUID().toString();
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setCreatedAt(LocalDateTime.now());

        return tokenEntity;
    }


    private UserView mapAsUserView(UserEntity user) {
        int apiCalls = tokenService.getApiCallsForUser(user.getToken().getToken());
        return new UserView(user.getUsername(), user.getEmail(), user.getToken().getToken(), apiCalls);
    }

    private UserEntity findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));
    }
}
