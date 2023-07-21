package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subjects,Long> {
}
