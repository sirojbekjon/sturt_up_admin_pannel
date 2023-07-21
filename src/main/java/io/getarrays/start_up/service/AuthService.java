package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.payload.Userdto;
import io.getarrays.start_up.repository.RoleRepository;
import io.getarrays.start_up.repository.UserRepository;
import io.getarrays.start_up.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public HttpEntity<?> saveUser(Userdto userdto) {
        try {
            boolean b = userRepository.existsByUsername(userdto.getUsername());
            if (!b) {
                Teacher teacher = new Teacher(
                        userdto.getName(),
                        userdto.getUsername(),
                        userdto.getLastName(),
                        passwordEncoder.encode(userdto.getPassword()),
                        UUID.randomUUID().toString(),
                        roleRepository.findByName("USER"),
                        false
                );
                userRepository.save(teacher);
                return ResponseEntity.status(202).body(teacher);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(500).body("Internal Server Error");
    }
}
