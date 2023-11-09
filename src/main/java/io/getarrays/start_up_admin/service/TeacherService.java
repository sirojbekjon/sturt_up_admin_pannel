package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.FileUpload;
import io.getarrays.start_up_admin.entity.Role;
import io.getarrays.start_up_admin.entity.Subjects;
import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.payload.TeachersDto;
import io.getarrays.start_up_admin.repository.FileUploadRepository;
import io.getarrays.start_up_admin.repository.RoleRepository;
import io.getarrays.start_up_admin.repository.SubjectRepository;
import io.getarrays.start_up_admin.repository.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            Optional<Subjects> optionalSubjects = subjectRepository.findById(teachersDto.getSubject());
            Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(teachersDto.getFilePhotoId());
            Optional<Role> optionalRole = roleRepository.findById(teachersDto.getRole());
            if (optionalFileUpload.isPresent() && optionalRole.isPresent() && optionalRole.isPresent()) {
                Subjects subjects = optionalSubjects.get();
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
                teachersRepository.save(teachers);
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
            Optional<Subjects> optionalSubjects = subjectRepository.findById(teachersDto.getSubject());
            Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(teachersDto.getFilePhotoId());
            Optional<Teacher> optionalTeachers = teachersRepository.findById(id);
            Optional<Role> optionalRole = roleRepository.findById(teachersDto.getRole());


            if (optionalFileUpload.isPresent() && optionalTeachers.isPresent() && optionalRole.isPresent() && optionalSubjects.isPresent()) {
                Subjects subjects = optionalSubjects.get();
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
                teachers.setSubject(subjects);
                teachers.setRole(role);
                teachersRepository.save(teachers);
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

    public HttpEntity<?> getAll() {
        List<Teacher> all = teachersRepository.findAll();
        if (!all.isEmpty()) {
            return ResponseEntity.status(202).body(all);
        }
        return ResponseEntity.status(404).body("Not found");
    }
}
