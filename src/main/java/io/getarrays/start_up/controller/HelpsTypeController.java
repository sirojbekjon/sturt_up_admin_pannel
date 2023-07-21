package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.HelpsType;
import io.getarrays.start_up.repository.HelpsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/helpsType")
public class HelpsTypeController {

    @Autowired
    HelpsTypeRepository helpsTypeRepository;

    @PostMapping("/add")
    public HttpEntity<?> addHelpsType(@RequestBody HelpsType helpsType){
        HelpsType helpsType1 = new HelpsType(
                helpsType.getName()
        );HelpsType saved = helpsTypeRepository.save(helpsType1);
        return ResponseEntity.status(200).body(saved+ " saved successfully");
    }

    @GetMapping("/get")
    public HttpEntity<?> getHelpsType(){
        List<HelpsType> helpsTypes = helpsTypeRepository.findAll();
        if (!helpsTypes.isEmpty()){
            return ResponseEntity.status(200).body(helpsTypes);
        }return ResponseEntity.status(404).body("Not Found");
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteHelpsType(@PathVariable Long id){
            helpsTypeRepository.deleteById(id);
        Optional<HelpsType> optionalHelpsType = helpsTypeRepository.findById(id);
        if (optionalHelpsType.isPresent()){
            return ResponseEntity.status(409).body("Not deleted");
        }return ResponseEntity.status(200).body("deleted successfully");
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editHelpsType(@PathVariable Long id,@RequestBody HelpsType helpsType){
        Optional<HelpsType> optionalHelpsType = helpsTypeRepository.findById(id);
        if (optionalHelpsType.isPresent()){
            HelpsType helpsType1 = optionalHelpsType.get();
            helpsType1.setName(helpsType.getName());
            helpsTypeRepository.save(helpsType1);
            return ResponseEntity.status(200).body("edited successfully");
        }
        return ResponseEntity.status(409).body("Do not edited");
    }

}
