package com.example.sbj.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CreateNewUserController {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserRepository customUserRepository;

    public CreateNewUserController(PasswordEncoder passwordEncoder, CustomUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepository = userRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        Optional<CustomUser> optionalUser = customUserRepository.findById(user.getUsername());
        if (!optionalUser.isPresent()) {
            customUserRepository.save(new CustomUser(user.getUsername(), passwordEncoder.encode(user.getPassword())));
            return ResponseEntity.ok("User created");
        }

        return ResponseEntity.ok("User already exists");
    }
}
