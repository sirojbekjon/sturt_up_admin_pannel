package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
