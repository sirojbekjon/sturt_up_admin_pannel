package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.FileUpload;
import io.getarrays.start_up.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileUploadService {

    @Autowired
    FileUploadRepository fileUploadRepository;

    public static final String UPLOAD_URL ="src/main/resources/static/upload";
//    public static final String UPLOAD_URL =System.getProperty("user.dir")+ "https://dev.vdocipher.com/api/videos/";

    public HttpEntity<?> upload(MultipartFile file) {
        if (file != null) {
            FileUpload fileUpload = new FileUpload();
            fileUpload.setOriginalName(file.getOriginalFilename());
            fileUpload.setContentType(file.getContentType());
            fileUpload.setSize(file.getSize());
            String originalName = file.getOriginalFilename();
            String[] split = originalName.split("\\.");

            String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
            fileUpload.setName(name);
            FileUpload savedFile = fileUploadRepository.save(fileUpload);
            File fileToSave = new File(UPLOAD_URL+"/"+name);
            if (!fileToSave.exists()) {
                fileToSave.mkdirs();
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return ResponseEntity.status(200).body(savedFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.status(409).body("File not saved");
    }


    public HttpEntity<?> deleteById(Long id) throws IOException {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(id);
        if (optionalFileUpload.isPresent()) {
            FileUpload fileUpload = optionalFileUpload.get();
            Path path = Paths.get(UPLOAD_URL + "/" + fileUpload.getName());
            Files.delete(path);
            fileUploadRepository.deleteById(id);
            return ResponseEntity.status(200).body("File deleted");
        }
        return ResponseEntity.status(404).body("File not found");
    }


    public HttpEntity<?> getFileUpload(Long id, HttpServletResponse response) throws IOException {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(id);
        if (optionalFileUpload.isPresent()) {
            FileUpload fileUpload = optionalFileUpload.get();
            Optional<FileUpload> optionalAttachment1 = fileUploadRepository.findById(id);
            if (optionalAttachment1.isPresent()) {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileUpload.getOriginalName() + "\"");
                response.setContentType(fileUpload.getContentType());
                FileInputStream fileInputStream = new FileInputStream(UPLOAD_URL + "/" + fileUpload.getName());
                FileCopyUtils.copy(fileInputStream, response.getOutputStream());
                return ResponseEntity.status(200).body(FileCopyUtils.copy(fileInputStream, response.getOutputStream()));
            }
        }
        return ResponseEntity.status(404).body("Not Found");
    }
}
