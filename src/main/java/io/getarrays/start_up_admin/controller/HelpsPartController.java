package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.payload.HelpsPartDto;
import io.getarrays.start_up_admin.repository.HelpsTypeRepository;
import io.getarrays.start_up_admin.service.HelpsPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/helpsPart")
public class HelpsPartController {

    @Autowired
    HelpsTypeRepository helpsTypeRepository;

    @Autowired
    HelpsPartService helpsPartService;
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PostMapping("/add")
    public HttpEntity<?> addHelpsPart(@RequestBody HelpsPartDto helpsPartDto){
        return helpsPartService.addHelpsPart(helpsPartDto);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @GetMapping("/get")
    public HttpEntity<?> getHelpsPart(){
        return helpsPartService.getHelpsPart();
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteHelpsPart(@PathVariable Long id){
        return helpsPartService.deleteHelpsPart(id);
    }
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editHelpsPart(@PathVariable Long id,@RequestBody HelpsPartDto helpsPartDto){
        return helpsPartService.editHelpsPart(id, helpsPartDto);
    }

}
