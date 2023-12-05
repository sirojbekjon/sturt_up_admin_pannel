package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.payload.SubjectDto;
import io.getarrays.start_up_admin.security.CurrentUser;
import io.getarrays.start_up_admin.service.SubjectService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Subjectni POST qilish uchun", notes = "Natija status ko'rinishida qaytadi")
    @PostMapping("/add")
    public HttpEntity<?> addSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.addSubject(subjectDto);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @ApiOperation(value = "Barcha subject larni get qilib olish uchun", notes = "natija barcha subjectlar qaytariladi")
    @GetMapping("/get")
    public HttpEntity<?> getSubjects(){
        return subjectService.getSubject();
    }


    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @ApiOperation(value = "Subjectni get qilish", notes = "natija subject qaytariladi")
    @GetMapping("/get/subject")
    public HttpEntity<?> getSubjectBySubjectId(@CurrentUser Teacher teacher){
        return subjectService.getSubjectById(teacher);
    }

    @PreAuthorize("hasAuthority('ADD_POST')")
    @ApiOperation(value = "Subjectni TypeId si orqali get qilish", notes = "natija subjectlar qaytariladi")
    @GetMapping("/getSubjects/{typeId}")
    public HttpEntity<?> getSubjectById(@PathVariable Long typeId, @CurrentUser Teacher teacher){
        return subjectService.getSubjectByTypeId(typeId,teacher);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @ApiOperation(value = "Subjectni id si orqali PUT qilish", notes = "natija subject o'zgartiriladi")
    @PutMapping("/edit/{id}")
    public HttpEntity<?>editSubject(@PathVariable Long id,@RequestBody SubjectDto subjectDto){
        return subjectService.editSubject(id, subjectDto);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @ApiOperation(value = "Subjectni id si orqali DELETE qilish", notes = "natija subject o'chiriladi")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteSubjectById(@PathVariable Long id){
        return subjectService.deleteById(id);
    }
}
