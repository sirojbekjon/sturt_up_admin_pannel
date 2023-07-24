package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subjects,Long> {
}
