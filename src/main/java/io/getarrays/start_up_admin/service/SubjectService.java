package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.*;
import io.getarrays.start_up_admin.payload.SubjectDto;
import io.getarrays.start_up_admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TypeSubjectRepository typeSubjectRepository;

    @Autowired
    TeachersRepository teachersRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;

    @Autowired
    ThemeSubjectRepository themeSubjectRepository;

    public HttpEntity<?> addSubject(SubjectDto subjectDto) {
        Optional<TypeSubject> optionalTypeSubject = typeSubjectRepository.findById(subjectDto.getTypeSubjectId());
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(subjectDto.getFileUploadId());
        if (optionalTypeSubject.isPresent() && optionalFileUpload.isPresent()){
            TypeSubject typeSubject = optionalTypeSubject.get();
            FileUpload fileUpload = optionalFileUpload.get();
            Subjects subjects1 = new Subjects(
                    subjectDto.getName(),
                    typeSubject,
                    subjectDto.getLanguage(),
                    subjectDto.getCourseCount(),
                    subjectDto.getPrice(),
                    subjectDto.getThemeCount(),
                    subjectDto.getQuestionCount(),
                    subjectDto.getAbout(),
                    fileUpload
            );
            Subjects savedSubject = subjectRepository.save(subjects1);
            return ResponseEntity.status(202).body(savedSubject+" saved successfully");
        }
        return ResponseEntity.status(404).body("Not found");
    }


    public HttpEntity<?> getSubject() {
        List<Subjects> subjects = subjectRepository.findAll();
        return ResponseEntity.status(202).body(subjects);
    }


    public HttpEntity<?> getSubjectById(Long typeId, Teacher teacher) {
        if (teacher.getRole().getName().equals("SUPERADMIN")) {
            List<Subjects> subjects = subjectRepository.subjectsByTypeSubjectId(typeId);
            if (!subjects.isEmpty()) {
                return ResponseEntity.status(202).body(subjects);
            } else {
                return ResponseEntity.status(404).body("Not Found");
            }
        }
        return ResponseEntity.status(409).body("Not found");
    }


    public HttpEntity<?> editSubject(Long id, SubjectDto subjectDto) {
        Optional<Subjects> optionalSubjects = subjectRepository.findById(id);
        Optional<TypeSubject> optionalTypeSubject = typeSubjectRepository.findById(subjectDto.getTypeSubjectId());
        if (optionalSubjects.isPresent() && optionalTypeSubject.isPresent()){
            TypeSubject typeSubject = optionalTypeSubject.get();
            Subjects subjects = optionalSubjects.get();
            subjects.setName(subjectDto.getName());
            subjects.setTypeSubject(typeSubject);
            Subjects savedSubject = subjectRepository.save(subjects);
            return ResponseEntity.status(202).body(savedSubject +" edit Successfully");
        }return ResponseEntity.status(404).body("Not Found");
    }

    public HttpEntity<?> deleteById(Long id) {
        subjectRepository.deleteById(id);
        Optional<Subjects> optionalSubjects = subjectRepository.findById(id);
        if (!optionalSubjects.isPresent()){
            return ResponseEntity.status(202).body("deleted");
        }
        return ResponseEntity.status(409).body("Not deleted");
    }
}
