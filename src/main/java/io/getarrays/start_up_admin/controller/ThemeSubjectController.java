package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.payload.ThemeSubjectDto;
import io.getarrays.start_up_admin.service.ThemeSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theme")
public class ThemeSubjectController {

    @Autowired
    ThemeSubjectService themeSubjectService;

    @PostMapping("/add")
    public HttpEntity<?> addThemeSubject(@RequestBody ThemeSubjectDto themeSubjectDto){
        return themeSubjectService.addThemesubject(themeSubjectDto);
    }

    @GetMapping("/get/{subjectId}")
    public HttpEntity<?> getThemeSubject(@PathVariable Long subjectId){
        return themeSubjectService.getThemeSubject(subjectId);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editThemeSubject(@PathVariable Long id,@RequestBody ThemeSubjectDto themeSubjectDto){
        return themeSubjectService.editThemesubject(id,themeSubjectDto);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteThemeSubject(@PathVariable Long id){
        return themeSubjectService.deleteThemeSubject(id);
    }

}
