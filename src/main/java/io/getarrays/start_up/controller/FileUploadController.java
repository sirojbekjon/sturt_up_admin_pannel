package io.getarrays.start_up.controller;

import io.getarrays.start_up.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public HttpEntity<?> uploadFile(@RequestBody MultipartFile files) throws IOException {
        return fileUploadService.upload(files);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> dleteFile(@PathVariable Long id) throws IOException {
        return fileUploadService.deleteById(id);
    }

    @GetMapping("/download/{id}")
    public HttpEntity<?> getFileUpload(@PathVariable Long id, HttpServletResponse response) throws IOException {
        return fileUploadService.getFileUpload(id, response);
    }
}
