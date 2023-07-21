package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {
}
