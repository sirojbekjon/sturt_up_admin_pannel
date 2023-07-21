package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.Role;
import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.payload.Userdto;
import io.getarrays.start_up.repository.RoleRepository;
import io.getarrays.start_up.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username+" Not found"));
    }

    public HttpEntity<?> editUser(Long id, Userdto userdto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        Role role = optionalRole.get();
        Optional<Teacher> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            Teacher teacher = optionalUser.get();
            teacher.setName(userdto.getName());
            teacher.setLastName(userdto.getLastName());
            teacher.setUsername(userdto.getUsername());
            teacher.setPassword(passwordEncoder.encode(userdto.getPassword()));
            teacher.setRole(role);
            Teacher save = userRepository.save(teacher);
            return ResponseEntity.status(200).body(save);
        }else {
            return ResponseEntity.status(409).body("User not found");
        }
    }





//
//    public ResponseEntity<?> addUser(User user) {
//        User newUser = new User(
//                user.getName(),
//                user.getUsername(),
//                user.getEmail(),
//                user.get
//        );
//
//
//    }
}
