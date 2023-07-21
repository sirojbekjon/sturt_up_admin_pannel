package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.FileUpload;
import io.getarrays.start_up.entity.Role;
import io.getarrays.start_up.entity.Subjects;
import io.getarrays.start_up.entity.Teacher;
import io.getarrays.start_up.payload.TeachersDto;
import io.getarrays.start_up.repository.FileUploadRepository;
import io.getarrays.start_up.repository.RoleRepository;
import io.getarrays.start_up.repository.SubjectRepository;
import io.getarrays.start_up.repository.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements UserDetailsService {

    private final TeachersRepository teachersRepository;
    private final SubjectRepository subjectRepository;
    private final FileUploadRepository fileUploadRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public HttpEntity<?> addTeacher(TeachersDto teachersDto) {
        try {
            List<Subjects> subjects = new ArrayList<>();

            for (Long subject : teachersDto.getSubjects()) {
                Optional<Subjects> optionalSubjects = subjectRepository.findById(subject);
                Subjects newsubject = optionalSubjects.get();
                subjects.add(newsubject);
            }

            Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(teachersDto.getFilePhotoId());
            Optional<Role> optionalRole = roleRepository.findById(teachersDto.getRole());
            if (optionalFileUpload.isPresent() && optionalRole.isPresent()) {
                Role role = optionalRole.get();
                Teacher teachers = new Teacher(
                teachersDto.getName(),
                teachersDto.getSureName(),
                teachersDto.getLastname(),
                teachersDto.getPhoneNumber(),
                teachersDto.getUsername(),
                passwordEncoder.encode(teachersDto.getPassword()),
                teachersDto.getAbout(),
                optionalFileUpload.get(),
                subjects,
                role);

                return ResponseEntity.status(200).body(teachers);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(409).body("Not saved");
    }

    //qayta mapped qilish kerak
    public HttpEntity<?> getTeacherById(Long id) {
        try{
            Optional<Teacher> optionalTeachers = teachersRepository.findById(id);
            if (optionalTeachers.isPresent())
                return ResponseEntity.status(200).body(optionalTeachers.get());
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    public HttpEntity<?> deleteTeacherById(Long id) {
        teachersRepository.deleteById(id);
        return ResponseEntity.status(200).body("Deleted");
    }

    //qayta mapped qilish kerak
    public HttpEntity<?> editTeacher(Long id, TeachersDto teachersDto) {
        try {
            List<Subjects> subjects = new ArrayList<>();

            for (Long subject : teachersDto.getSubjects()) {
                Optional<Subjects> optionalSubjects = subjectRepository.findById(subject);
                Subjects newsubject = optionalSubjects.get();
                subjects.add(newsubject);
            }

            Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(teachersDto.getFilePhotoId());
            Optional<Teacher> optionalTeachers = teachersRepository.findById(id);
            Optional<Role> optionalRole = roleRepository.findById(teachersDto.getRole());


            if (optionalFileUpload.isPresent() && optionalTeachers.isPresent() && optionalRole.isPresent()) {
                Teacher teachers = optionalTeachers.get();
                Role role = optionalRole.get();

                teachers.setName(teachersDto.getName());
                teachers.setSureName(teachersDto.getSureName());
                teachers.setLastName(teachersDto.getLastname());
                teachers.setPhoneNumber(teachersDto.getPhoneNumber());
                teachers.setUsername(teachersDto.getUsername());
                teachers.setPassword(passwordEncoder.encode(teachersDto.getPassword()));
                teachers.setAbout(teachersDto.getAbout());
                teachers.setFileUpload(optionalFileUpload.get());
                teachers.setSubjectsList(subjects);
                teachers.setRole(role);

                return ResponseEntity.status(200).body(teachers);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(409).body("Not edited");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teachersRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username+" Not found"));

    }
}
