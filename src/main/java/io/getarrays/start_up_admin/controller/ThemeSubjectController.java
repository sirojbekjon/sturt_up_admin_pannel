package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.payload.ThemeSubjectDto;
import io.getarrays.start_up_admin.security.CurrentUser;
import io.getarrays.start_up_admin.service.ThemeSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/themeSubject")
public class ThemeSubjectController {

    @Autowired
    ThemeSubjectService themeSubjectService;

    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addThemeSubject(@Valid @RequestBody ThemeSubjectDto themeSubjectDto,@ApiIgnore @CurrentUser Teacher teacher){
        return themeSubjectService.addThemesubject(themeSubjectDto,teacher);
    }

    @PreAuthorize("hasAuthority('ADD_POST')")
    @GetMapping("/get/{subjectId}/{teacherId}")
    public HttpEntity<?> getThemeSubject(@PathVariable Long subjectId,@PathVariable Long teacherId){
        return themeSubjectService.getThemeSubject(subjectId,teacherId);
    }

    @PreAuthorize("hasAuthority('ADD_POST')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editThemeSubject(@PathVariable Long id,@RequestBody ThemeSubjectDto themeSubjectDto){
        return themeSubjectService.editThemesubject(id,themeSubjectDto);
    }

    @PreAuthorize("hasAuthority('ADD_POST')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteThemeSubject(@PathVariable Long id){
        return themeSubjectService.deleteThemeSubject(id);
    }
}
