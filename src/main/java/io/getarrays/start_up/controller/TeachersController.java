package io.getarrays.start_up.controller;

import io.getarrays.start_up.payload.TeachersDto;
import io.getarrays.start_up.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeachersController {

    @Autowired
    TeacherService teacherService;


    @PostMapping("/add")
    public HttpEntity<?> addTeacher(@RequestBody TeachersDto teachersDto){
        return teacherService.addTeacher(teachersDto);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getTeacher(@PathVariable Long id){
        return teacherService.getTeacherById(id);
    }

       @DeleteMapping("/delete/{id}")
    public HttpEntity<?> DeleteTeacher(@PathVariable Long id){
        return teacherService.deleteTeacherById(id);
    }


    @PutMapping("/edit/{id}")
    public HttpEntity<?> editTeacher(@PathVariable Long id,@RequestBody TeachersDto teachersDto){
        return teacherService.editTeacher(id,teachersDto);
    }



}
