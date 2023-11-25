package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.PartOfTheme;
import io.getarrays.start_up_admin.payload.PartOfThemeDto;
import io.getarrays.start_up_admin.repository.PartOfThemeRepository;
import io.getarrays.start_up_admin.service.PartOfThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/part_theme")
public class PartOfthemeController {

    @Autowired
    PartOfThemeRepository partOfThemeRepository;

    @Autowired
    PartOfThemeService partOfThemeService;
    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addPartOfTheme(@RequestBody PartOfThemeDto partOfThemeDto){
        return partOfThemeService.addPartOfTheme(partOfThemeDto);
    }
//    @PreAuthorize("hasAuthority('ADD_POST')")
    @GetMapping("/get/{themeSubjectId}")
    public HttpEntity<?> getAllPartOftheme(@PathVariable Long themeSubjectId){
        List<PartOfTheme> partOfThemes = partOfThemeRepository.findByThemeSubject(themeSubjectId);
        if (!partOfThemes.isEmpty()) {
        return ResponseEntity.status(202).body(partOfThemes);
        }
        return ResponseEntity.status(409).body("Not found");
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        Optional<PartOfTheme> partOfThemes = partOfThemeRepository.findById(id);
        if (!partOfThemes.isPresent()) {
        return ResponseEntity.status(202).body(partOfThemes.get());
        }
        return ResponseEntity.status(409).body("Not found");
    }


    @PreAuthorize("hasAuthority('ADD_POST')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editPartOfTheme(@PathVariable Long id, @RequestBody PartOfThemeDto partOfThemeDto){
        return partOfThemeService.editPartOfTheme(id,partOfThemeDto);
    }

    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deletePartOfTheme(@PathVariable Long id){
        partOfThemeRepository.deleteById(id);
        Optional<PartOfTheme> byId = partOfThemeRepository.findById(id);
        if (!byId.isPresent()){
            return ResponseEntity.status(202).body("deleted");
        }
        return ResponseEntity.status(409).body("Not deleted");
    }

}
