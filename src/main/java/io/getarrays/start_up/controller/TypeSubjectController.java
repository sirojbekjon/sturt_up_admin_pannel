package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.TypeSubject;
import io.getarrays.start_up.repository.TypeSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typeSubject")
public class TypeSubjectController {

    @Autowired
    TypeSubjectRepository typeSubjectRepository;


    @PostMapping("/add")
    public HttpEntity<?> addTypeSubject(@RequestBody TypeSubject typeSubject){
        TypeSubject typeSubject1 = new TypeSubject(
                typeSubject.getName()
        );
        TypeSubject savedType = typeSubjectRepository.save(typeSubject1);
    return ResponseEntity.status(202).body(savedType +" Saved");
    }



    @GetMapping("/get")
    public HttpEntity<?> getTypeSubject(){
        List<TypeSubject> typeSubjects = typeSubjectRepository.findAll();
        return ResponseEntity.status(202).body(typeSubjects);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getTypeSubjectById(@PathVariable Long id){
        Optional<TypeSubject> optionalTypeSubject = typeSubjectRepository.findById(id);
        if (optionalTypeSubject.isPresent()){
            TypeSubject typeSubject = optionalTypeSubject.get();
            return ResponseEntity.status(202).body(typeSubject);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @GetMapping("/delete/{id}")
    public HttpEntity<?> deleteTypeSubject(@PathVariable Long id){
        Optional<TypeSubject> optionalTypeSubject = typeSubjectRepository.findById(id);
        if (optionalTypeSubject.isPresent()){
            typeSubjectRepository.deleteById(id);
            return ResponseEntity.status(202).body("Deleted");
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }


    @PutMapping("/edit/{id}")
    public HttpEntity<?> editTypeSubject(@PathVariable Long id,@RequestBody TypeSubject typeSubject){
        Optional<TypeSubject> optionalTypeSubject = typeSubjectRepository.findById(id);
        if (optionalTypeSubject.isPresent()){
            TypeSubject typeSubject1 = optionalTypeSubject.get();
            typeSubject1.setName(typeSubject.getName());
            TypeSubject savedTypeSubject = typeSubjectRepository.save(typeSubject1);
            return ResponseEntity.status(202).body(savedTypeSubject);
        }else {
            return ResponseEntity.status(404).body("Not Found");
        }
    }
}
