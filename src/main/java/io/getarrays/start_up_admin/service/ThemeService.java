package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.FileUpload;
import io.getarrays.start_up_admin.entity.PartOfTheme;
import io.getarrays.start_up_admin.entity.Theme;
import io.getarrays.start_up_admin.payload.ThemeDto;
import io.getarrays.start_up_admin.repository.FileUploadRepository;
import io.getarrays.start_up_admin.repository.PartOfThemeRepository;
import io.getarrays.start_up_admin.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    PartOfThemeRepository partOfThemeRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;
    public HttpEntity<?> addtheme(ThemeDto themeDto) {
        Theme saved = null;
        try {
            Optional<PartOfTheme> partOfThemeRepositoryById = partOfThemeRepository.findById(themeDto.getPartOfThemeId());
            Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(themeDto.getFilUploadId());
            Theme theme = new Theme(
                    themeDto.getNumber(),
                    themeDto.getName(),
                    themeDto.isState(),
                    partOfThemeRepositoryById.get(),
                    optionalFileUpload.get()
            );
           saved = themeRepository.save(theme);
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(202).body(saved);
    }

    public HttpEntity<?> getThemeByPartOftheme(Long partOfthemeId) {
        List<Theme> byPartOfThem = null;
        try {
            byPartOfThem = themeRepository.findByPartOfThem(partOfthemeId);
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(202).body(byPartOfThem);
    }

    public HttpEntity<?> editTheme(Long id,ThemeDto themeDto) {
        try {
            Optional<Theme> optionalTheme = themeRepository.findById(id);
            Theme newTheme = optionalTheme.get();
            Optional<PartOfTheme> partOfThemeRepositoryById = partOfThemeRepository.findById(themeDto.getPartOfThemeId());
            Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(themeDto.getFilUploadId());
            newTheme.setName(themeDto.getName());
            newTheme.setNumber(themeDto.getNumber());
            newTheme.setState(themeDto.isState());
            newTheme.setPartOfTheme(partOfThemeRepositoryById.get());
            newTheme.setFileUpload(optionalFileUpload.get());
            return ResponseEntity.status(202).body(themeRepository.save(newTheme));
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(409).body("Not changed");
    }
}
