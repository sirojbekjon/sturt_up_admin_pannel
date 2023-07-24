package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
