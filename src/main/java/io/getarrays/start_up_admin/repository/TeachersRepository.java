package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeachersRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUsername(String username);
}
