package robsunApi.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import robsunApi.domain.entity.RoleEntity;
import robsunApi.domain.entity.UserEntity;
import robsunApi.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private static final String USER_NOT_FOUND = "User not found for email: ";
    private static final String ROLE_PREFIX = "ROLE_";

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(AppUserDetailsService::mapFromEntity)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + email));
    }
    private static UserDetails mapFromEntity(UserEntity userEntity) {

        return new CustomUser(
                userEntity.getEmail(), // Use email as username
                userEntity.getPassword(),
                userEntity.getRoles()
                        .stream()
                        .map(AppUserDetailsService::mapRole)
                        .toList(),
                userEntity.getUsername()
        );
    }

    private static GrantedAuthority mapRole(RoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority(ROLE_PREFIX + userRoleEntity.getName().name());
    }
}
