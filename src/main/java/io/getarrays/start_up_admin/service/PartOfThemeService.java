package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.PartOfTheme;
import io.getarrays.start_up_admin.entity.ThemeSubject;
import io.getarrays.start_up_admin.payload.PartOfThemeDto;
import io.getarrays.start_up_admin.repository.PartOfThemeRepository;
import io.getarrays.start_up_admin.repository.ThemeSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartOfThemeService {


    @Autowired
    PartOfThemeRepository partOfThemeRepository;

    @Autowired
    ThemeSubjectRepository themeSubjectRepository;
    public HttpEntity<?> addPartOfTheme(PartOfThemeDto partOfThemeDto) {
        try {
        Optional<ThemeSubject> themeSubject = themeSubjectRepository.findById(partOfThemeDto.getThemeSubjectId());
        if (themeSubject.isPresent()){
        PartOfTheme partOfTheme = new PartOfTheme(
                partOfThemeDto.getName(),
                partOfThemeDto.getNumber(),
                themeSubject.get()
        );
        return ResponseEntity.status(202).body(partOfThemeRepository.save(partOfTheme));
        }
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(409).body("Not saved");
    }

    public HttpEntity<?> editPartOfTheme(Long id,PartOfThemeDto partOfThemeDto) {
        Optional<PartOfTheme> partOfTheme = partOfThemeRepository.findById(id);
        Optional<ThemeSubject> themeSubject = themeSubjectRepository.findById(partOfThemeDto.getThemeSubjectId());
        if (partOfTheme.isPresent() && themeSubject.isPresent()){
            ThemeSubject new_themeSubject = themeSubject.get();
            PartOfTheme new_partOfTheme = partOfTheme.get();
            new_partOfTheme.setName(partOfThemeDto.getName());
            new_partOfTheme.setNumber(partOfThemeDto.getNumber());
            new_partOfTheme.setThemeSubject(new_themeSubject);
            PartOfTheme saved_theme = partOfThemeRepository.save(new_partOfTheme);
            return ResponseEntity.status(202).body(saved_theme);
        }
        return ResponseEntity.status(409).body("Not saved");
    }
}
