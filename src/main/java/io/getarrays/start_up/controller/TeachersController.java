package io.getarrays.start_up.controller;

import io.getarrays.start_up.payload.TeachersDto;
import io.getarrays.start_up.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeachersController {


    private final TeacherService teacherService;


    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping("/add")
    public HttpEntity<?> addTeacher(@RequestBody TeachersDto teachersDto){
        return teacherService.addTeacher(teachersDto);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getTeacher(@PathVariable Long id){
        return teacherService.getTeacherById(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> DeleteTeacher(@PathVariable Long id){
        return teacherService.deleteTeacherById(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editTeacher(@PathVariable Long id,@RequestBody TeachersDto teachersDto){
        return teacherService.editTeacher(id,teachersDto);
    }
}
