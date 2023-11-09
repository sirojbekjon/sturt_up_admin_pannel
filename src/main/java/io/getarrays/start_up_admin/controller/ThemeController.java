package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Theme;
import io.getarrays.start_up_admin.payload.ThemeDto;
import io.getarrays.start_up_admin.repository.ThemeRepository;
import io.getarrays.start_up_admin.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Theme")
public class ThemeController {

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    ThemeService themeService;
    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addTheme(@RequestBody ThemeDto themeDto){
        return themeService.addtheme(themeDto);
    }


    @PreAuthorize("hasAuthority('ADD_POST')")
    @GetMapping("/get/{partOfthemeId}")
    public HttpEntity<?> getAllTheme(@PathVariable Long partOfthemeId){
        return themeService.getThemeByPartOftheme(partOfthemeId);
    }
    @PreAuthorize("hasAuthority('ADD_POST')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteTheme(@PathVariable Long id){
        themeRepository.deleteById(id);
        Optional<Theme> byId = themeRepository.findById(id);
        if (!byId.isPresent()){
            return ResponseEntity.status(202).body("Deleted");
        }
        return ResponseEntity.status(409).body("Not deleted");
    }
    @PreAuthorize("hasAuthority('ADD_POST')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editTheme(@PathVariable Long id,ThemeDto themeDto){
        return themeService.editTheme(id,themeDto);
    }



}
