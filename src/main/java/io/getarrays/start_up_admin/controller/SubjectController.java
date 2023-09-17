package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.payload.SubjectDto;
import io.getarrays.start_up_admin.security.CurrentUser;
import io.getarrays.start_up_admin.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PostMapping("/add")
    public HttpEntity<?> addSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.addSubject(subjectDto);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @GetMapping("/get")
    public HttpEntity<?> getSubjects(){
        return subjectService.getSubject();
    }
    @PreAuthorize("hasAuthority('ADD_POST')")
    @GetMapping("/get/{id}")
    public HttpEntity<?> getSubjectById(@PathVariable Long id, @CurrentUser Teacher teacher){
        return subjectService.getSubjectById(id,teacher);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?>editSubject(@PathVariable Long id,@RequestBody SubjectDto subjectDto){
        return subjectService.editSubject(id, subjectDto);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteSubjectById(@PathVariable Long id){
        return subjectService.deleteById(id);
    }








}
