package robsunApi.service;

import org.springframework.stereotype.Service;
import robsunApi.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser() {

    }

}
