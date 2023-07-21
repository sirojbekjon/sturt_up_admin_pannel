package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUsername(String username);
}
