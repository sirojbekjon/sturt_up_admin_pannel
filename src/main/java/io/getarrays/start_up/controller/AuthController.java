package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.payload.LoginDto;
import io.getarrays.start_up.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            Teacher teacher = (Teacher) authentication.getPrincipal();
            String token = jwtProvider.generateToken(teacher.getUsername(), teacher.getRole());
            return ResponseEntity.status(200).body(token);
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(404).body("User not found");
    }
}



