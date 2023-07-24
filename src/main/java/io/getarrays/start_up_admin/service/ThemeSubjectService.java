package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.Subjects;
import io.getarrays.start_up_admin.entity.ThemeSubject;
import io.getarrays.start_up_admin.payload.ThemeSubjectDto;
import io.getarrays.start_up_admin.repository.SubjectRepository;
import io.getarrays.start_up_admin.repository.ThemeSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThemeSubjectService {

    @Autowired
    ThemeSubjectRepository themeSubjectRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public HttpEntity<?> addThemesubject(ThemeSubjectDto themeSubjectDto) {
        Optional<Subjects> optionalSubjects = subjectRepository.findById(themeSubjectDto.getSubjectId());
        ThemeSubject themeSubject = new ThemeSubject(
                themeSubjectDto.getName(),
                optionalSubjects.get()
        );
        ThemeSubject savedthemesubject = themeSubjectRepository.save(themeSubject);
        return ResponseEntity.status(200).body(savedthemesubject+ " saved successfully");
    }




    public HttpEntity<?> editThemesubject(Long id, ThemeSubjectDto themeSubjectDto) {
        Optional<Subjects> optionalSubjects = subjectRepository.findById(themeSubjectDto.getSubjectId());
        Optional<ThemeSubject> optionalThemeSubject = themeSubjectRepository.findById(id);
        if (optionalThemeSubject.isPresent() && optionalSubjects.isPresent()){
            ThemeSubject themeSubject = optionalThemeSubject.get();
            themeSubject.setName(themeSubjectDto.getName());
            themeSubject.setSubjects(optionalSubjects.get());
            ThemeSubject editedThemeSubject = themeSubjectRepository.save(themeSubject);
            return ResponseEntity.status(200).body(editedThemeSubject);
        }
        return ResponseEntity.status(404).body("Not Found");
    }


    public HttpEntity<?> getThemeSubject(Long subjectId) {
        Optional<Subjects> optionalSubjects = subjectRepository.findById(subjectId);
        Optional<ThemeSubject> themeSubject = themeSubjectRepository.findBySubjects(optionalSubjects.get());
        if (themeSubject.isPresent())
            return ResponseEntity.status(200).body(themeSubject.get());
        return ResponseEntity.status(404).body("Not Found");
    }

    public HttpEntity<?> deleteThemeSubject(Long id) {
        themeSubjectRepository.deleteById(id);
        return ResponseEntity.status(200).body("Deleted");
    }
}
