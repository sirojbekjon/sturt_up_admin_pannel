package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping("/upload")
    public HttpEntity<?> uploadFile(@RequestBody MultipartFile files){
        return fileUploadService.upload(files);
    }
    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> dleteFile(@PathVariable Long id) throws IOException {
        return fileUploadService.deleteById(id);
    }
    @PreAuthorize("hasAuthority('ADD_POST')")
    @GetMapping("/download/{id}")
    public HttpEntity<?> getFileUpload(@PathVariable Long id, HttpServletResponse response) throws IOException {
        return fileUploadService.getFileUpload(id, response);
    }
}
