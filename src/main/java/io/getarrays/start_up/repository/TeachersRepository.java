package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersRepository extends JpaRepository<Teachers, Long> {
}
