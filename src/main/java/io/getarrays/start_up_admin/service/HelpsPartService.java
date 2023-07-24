package io.getarrays.start_up_admin.service;

import io.getarrays.start_up_admin.entity.FileUpload;
import io.getarrays.start_up_admin.entity.HelpsPart;
import io.getarrays.start_up_admin.entity.HelpsType;
import io.getarrays.start_up_admin.payload.HelpsPartDto;
import io.getarrays.start_up_admin.repository.FileUploadRepository;
import io.getarrays.start_up_admin.repository.HelpsPartRepository;
import io.getarrays.start_up_admin.repository.HelpsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HelpsPartService {

    @Autowired
    HelpsPartRepository helpsPartRepository;

    @Autowired
    HelpsTypeRepository helpsTypeRepository;

    @Autowired
    FileUploadRepository fileUploadRepository;

    public HttpEntity<?> addHelpsPart(HelpsPartDto helpsPartDto) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(helpsPartDto.getFileId());
        Optional<HelpsType> optionalHelpsType = helpsTypeRepository.findById(helpsPartDto.getHelpstypeId());

        if (optionalHelpsType.isPresent() && optionalFileUpload.isPresent()){
            HelpsPart helpsPart = new HelpsPart(
                    helpsPartDto.getText(),
                    optionalHelpsType.get(),
                    (List<FileUpload>) optionalFileUpload.get()
            );
            HelpsPart saved = helpsPartRepository.save(helpsPart);
            return ResponseEntity.status(200).body(saved + " Successfully");
        }
        return ResponseEntity.status(409).body("Do not saved");

    }

    public HttpEntity<?> getHelpsPart() {
        List<HelpsPart> helpsParts = helpsPartRepository.findAll();
        return ResponseEntity.status(200).body(helpsParts);
    }

    public HttpEntity<?> deleteHelpsPart(Long id) {
        helpsPartRepository.deleteById(id);
        return ResponseEntity.status(200).body("Deleted");
    }

    public HttpEntity<?> editHelpsPart(Long id, HelpsPartDto helpsPartDto) {
        Optional<HelpsPart> helpsPartOptional = helpsPartRepository.findById(id);
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(helpsPartDto.getFileId());
        Optional<HelpsType> optionalHelpsType = helpsTypeRepository.findById(helpsPartDto.getHelpstypeId());
        if (helpsPartOptional.isPresent()){
            HelpsPart helpsPart = helpsPartOptional.get();
            helpsPart.setText(helpsPartDto.getText());
            helpsPart.setHelpsType(optionalHelpsType.get());
            helpsPart.setFileUpload((List<FileUpload>) optionalFileUpload.get());
            HelpsPart edited = helpsPartRepository.save(helpsPart);
            return ResponseEntity.status(200).body(edited);
        }
        return ResponseEntity.status(409).body("Not edit");
    }
}
