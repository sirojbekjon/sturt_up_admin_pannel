package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.payload.LoginDto;
import io.getarrays.start_up.payload.Userdto;
import io.getarrays.start_up.repository.UserRepository;
import io.getarrays.start_up.security.JwtProvider;
import io.getarrays.start_up.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/register")
    public HttpEntity<?> saveUser(@RequestBody Userdto userdto) {
        return ResponseEntity.status(202).body(authService.saveUser(userdto));
    }

    @PostMapping("/login")
    public HttpEntity<?>loginToSystem(@RequestBody  LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        Teacher teacher = (Teacher)authentication.getPrincipal();
        String token = jwtProvider.generateToken(teacher.getUsername(), teacher.getRole());
        return ResponseEntity.status(200).body(token);
    }


    @GetMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String emailCode,@RequestParam String email){
        return authService.verifyEmail(emailCode, email);
    }

}


