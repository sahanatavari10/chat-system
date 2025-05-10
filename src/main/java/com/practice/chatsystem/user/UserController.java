package com.practice.chatsystem.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserDTO userDTO){
        User user = userService.registerFor(userDTO);
        if(user==null){
            return ResponseEntity.badRequest().body("User already exists try to login!");
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResult> loginUser(@RequestParam String userName, @RequestParam String password){
        UserLoginResult result = userService.loginFor(userName, password);
        return switch (result){
            case UserLoginResult.IsPresent isPresent -> ResponseEntity.ok(isPresent);
            case UserLoginResult.InvalidCredentials ignored -> ResponseEntity.badRequest().build();
            case UserLoginResult.NotFound ignored -> ResponseEntity.notFound().build();
        };
    }
}
