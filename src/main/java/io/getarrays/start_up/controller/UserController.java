package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.payload.Userdto;
import io.getarrays.start_up.repository.UserRepository;
import io.getarrays.start_up.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;



//    @PostMapping("/add")
//    public ResponseEntity<?> addUser(@RequestBody User user){
//        userService.addUser(user)
//    }




    @GetMapping("/users")
    public ResponseEntity<List<Teacher>> getUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
   }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteUser(@Valid @PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.status(202).body("User have deleted");
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editUser(@Valid @PathVariable Long id, @RequestBody Userdto userdto){
        return userService.editUser(id, userdto);
    };


//   @GetMapping("/roles")
//   public HttpEntity<?> getRoles(){
//       List<Role> allRole = roleRepository.findAll();
//       return ResponseEntity.status(200).body(allRole);
//   };

//   record AddRoleToUserRequest(@JsonProperty ("username") String username,@JsonProperty("roleId") Long roleId){};
//   record AddRoleToUserResponse(@JsonProperty Long id,@JsonProperty("user_name") String username){};
//   @PostMapping("/role/addtouser")
//   public ResponseEntity<User> addRoleToUser(@RequestBody AddRoleToUserRequest addRoleToUserRequest) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/addtouser").toUriString());
//        return ResponseEntity.created(uri).body(userService.addRoleToUser(addRoleToUserRequest.username(),addRoleToUserRequest.roleId()));
//    }

}





