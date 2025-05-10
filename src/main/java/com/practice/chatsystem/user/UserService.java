package com.practice.chatsystem.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerFor(UserDTO userDTO){
        Optional<User> maybeUser = userRepository.findByUserName(userDTO.getUserName());
        if(maybeUser.isPresent()) {
            return null;
        }
        User user = new User(userDTO.getUserName(), passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public UserLoginResult loginFor(String userName, String password){
        Optional<User> maybeUser = userRepository.findByUserName(userName);
        if(maybeUser.isPresent()) {
            if (passwordEncoder.matches(password, maybeUser.get().getPassword())) {
                return new UserLoginResult.IsPresent(maybeUser.get());
            }
            return new UserLoginResult.InvalidCredentials();
        }
        return new UserLoginResult.NotFound();
    }
}
