package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.User;
import io.getarrays.start_up.payload.LoginDto;
import io.getarrays.start_up.payload.Userdto;
import io.getarrays.start_up.repository.RoleRepository;
import io.getarrays.start_up.repository.UserRepository;
import io.getarrays.start_up.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
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
                User user = new User(
                        userdto.getName(),
                        userdto.getUsername(),
                        userdto.getLastName(),
                        passwordEncoder.encode(userdto.getPassword()),
                        UUID.randomUUID().toString(),
                        roleRepository.findByName("USER"),
                        false
                );
                userRepository.save(user);
//                boolean sendEmail = emailService.sendEmail(user.getEmail(), user.getEmailCode());
                return ResponseEntity.status(202).body(user);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(500).body("Internal Server Error");
    }

//    public HttpEntity<?> verifyEmail(String emailCode, String email) {
//        Optional<User> optionalUser = userRepository.findByEmailCode(emailCode);
//        if (optionalUser.isPresent()){
//            User user = optionalUser.get();
//            user.setEnabled(true);
//            user.setEmailCode(null);
//            userRepository.save(user);
//            return ResponseEntity.status(202).body("Email has been active");
//        }else {
//            return ResponseEntity.status(404).body("User Not Found");
//        }
//    }
}
