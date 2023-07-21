package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Subjects;
import io.getarrays.start_up.entity.ThemeSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeSubjectRepository extends JpaRepository<ThemeSubject, Long> {

    Optional<ThemeSubject> findBySubjects(Subjects subjects);
}
