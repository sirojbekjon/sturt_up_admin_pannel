package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.FileUpload;
import io.getarrays.start_up.entity.Teachers;
import io.getarrays.start_up.payload.TeachersDto;
import io.getarrays.start_up.repository.FileUploadRepository;
import io.getarrays.start_up.repository.SubjectRepository;
import io.getarrays.start_up.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeachersRepository teachersRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;

    public HttpEntity<?> addTeacher(TeachersDto teachersDto) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(teachersDto.getFilePhotoId());

        if (optionalFileUpload.isPresent()){
            FileUpload fileUpload = optionalFileUpload.get();
            Teachers teachers = new Teachers(
                    teachersDto.getName(),
                    teachersDto.getLastname(),
                    teachersDto.getSurename(),
                    teachersDto.getAbout(),
                    fileUpload
            );
            return ResponseEntity.status(200).body(teachers);
        }return ResponseEntity.status(409).body("Not saved");
    }


    public HttpEntity<?> getTeacherById(Long id) {
        Optional<Teachers> optionalTeachers = teachersRepository.findById(id);
        if (optionalTeachers.isPresent())
            return ResponseEntity.status(200).body(optionalTeachers.get());
        return ResponseEntity.status(404).body("Not Found");
    }


    public HttpEntity<?> deleteTeacherById(Long id) {
        teachersRepository.deleteById(id);
        return ResponseEntity.status(200).body("Deleted");
    }


    public HttpEntity<?> editTeacher(Long id, TeachersDto teachersDto) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(teachersDto.getFilePhotoId());
        Optional<Teachers> optionalTeachers = teachersRepository.findById(id);
        if (optionalFileUpload.isPresent() && optionalTeachers.isPresent()){
            Teachers teachers = optionalTeachers.get();
            teachers.setName(teachersDto.getName());
            teachers.setLastname(teachersDto.getLastname());
            teachers.setSurename(teachersDto.getSurename());
            teachers.setAbout(teachersDto.getAbout());
            teachers.setFileUpload(optionalFileUpload.get());
            return ResponseEntity.status(200).body(teachers);
        }
        return ResponseEntity.status(409).body("Not edited");
    }


}
