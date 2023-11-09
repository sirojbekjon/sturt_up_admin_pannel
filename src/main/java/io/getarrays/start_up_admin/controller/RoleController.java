package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Role;
import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.repository.RoleRepository;
import io.getarrays.start_up_admin.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;




    @GetMapping("/get/all")
    public HttpEntity<?> getRoles(@ApiIgnore @CurrentUser Teacher teacher){
         return (teacher.getRole().getName().equals("SUPERADMIN")) ? ResponseEntity.status(202).body(roleRepository.findAll()) : ResponseEntity.status(409).body("Not found");
    }



    @GetMapping("/get/{roleId}")
    public HttpEntity<?> getRoleById(@PathVariable Long roleId, @ApiIgnore @CurrentUser Teacher teacher){
        return (teacher.getRole().getName().equals("SUPERADMIN")) ? ResponseEntity.status(202).body(roleRepository.findById(roleId)) : ResponseEntity.status(409).body("Not found");
    }



}
