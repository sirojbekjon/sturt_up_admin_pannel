package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.payload.SubjectDto;
import io.getarrays.start_up_admin.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("/add")
    public HttpEntity<?> addSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.addSubject(subjectDto);
    }

    @GetMapping("/get")
    public HttpEntity<?> getSubjects(){
        return subjectService.getSubject();
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?>editSubject(@PathVariable Long id,@RequestBody SubjectDto subjectDto){
        return subjectService.editSubject(id, subjectDto);
    }









}
