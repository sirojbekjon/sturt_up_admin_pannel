package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.payload.LoginDto;
import io.getarrays.start_up_admin.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
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



