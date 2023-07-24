package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.entity.Footer;
import io.getarrays.start_up_admin.repository.FooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/footer")
public class FooterController {

    @Autowired
    FooterRepository footerRepository;

    @PostMapping("/add")
    public HttpEntity<?> addFooter(@RequestBody Footer footer){
        Footer newFooter = new Footer(
                footer.getText(),
                footer.getInstagram(),
                footer.getYoutube(),
                footer.getTelegram(),
                footer.getPhone(),
                footer.isStatus()
        );
        Footer save = footerRepository.save(newFooter);
        return ResponseEntity.status(202).body(save+" saved successfully");
    }

    @GetMapping("/get")
    public HttpEntity<?> getFooter(){
        List<Footer> footerList = footerRepository.findAll();
        return ResponseEntity.status(202).body(footerList);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editFooter(@PathVariable Long id,@RequestBody Footer footer){
        Optional<Footer> optionalFooter = footerRepository.findById(id);
        if (optionalFooter.isPresent()){
            Footer footer1 = optionalFooter.get();
            footer1.setInstagram(footer.getInstagram());
            footer1.setText(footer.getText());
            footer1.setPhone(footer.getPhone());
            footer1.setYoutube(footer.getYoutube());
            footer1.setTelegram(footer.getTelegram());
            footer1.setStatus(footer.isStatus());
            footerRepository.save(footer1);
            return ResponseEntity.status(202).body("Edited successfully");
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteFooter(@PathVariable Long id){
        footerRepository.deleteById(id);
        Optional<Footer> optionalFooter = footerRepository.findById(id);
        if (optionalFooter.isPresent()){
            return ResponseEntity.status(409).body("Not deleted");
        }
        return ResponseEntity.status(200).body("Footer deleted");
    }


}
