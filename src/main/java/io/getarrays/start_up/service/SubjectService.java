package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.FileUpload;
import io.getarrays.start_up.entity.Subjects;
import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.entity.TypeSubject;
import io.getarrays.start_up.payload.SubjectDto;
import io.getarrays.start_up.repository.FileUploadRepository;
import io.getarrays.start_up.repository.SubjectRepository;
import io.getarrays.start_up.repository.TeachersRepository;
import io.getarrays.start_up.repository.TypeSubjectRepository;
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

    public HttpEntity<?> addSubject(SubjectDto subjectDto) {
        Optional<TypeSubject> optionalTypeSubject = typeSubjectRepository.findById(subjectDto.getTypeSubjectId());
        Optional<Teacher> optionalTeachers = teachersRepository.findById(subjectDto.getTeacherId());
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(subjectDto.getFileUploadId());
        if (optionalTypeSubject.isPresent() && optionalTeachers.isPresent() && optionalFileUpload.isPresent()){
            TypeSubject typeSubject = optionalTypeSubject.get();
            Teacher teachers = optionalTeachers.get();
            FileUpload fileUpload = optionalFileUpload.get();
            Subjects subjects1 = new Subjects(
                    subjectDto.getName(),
                    typeSubject,
                    teachers,
                    subjectDto.getLanguage(),
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


    public HttpEntity<?> getSubjectById(Long id) {
        Optional<Subjects> optionalSubjects = subjectRepository.findById(id);
        if (optionalSubjects.isPresent()){
            Subjects subject = optionalSubjects.get();
            return ResponseEntity.status(202).body(subject);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
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
}
