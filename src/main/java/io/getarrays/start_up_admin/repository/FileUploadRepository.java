package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
