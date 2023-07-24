package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Subjects;
import io.getarrays.start_up_admin.entity.ThemeSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeSubjectRepository extends JpaRepository<ThemeSubject, Long> {

    Optional<ThemeSubject> findBySubjects(Subjects subjects);
}
