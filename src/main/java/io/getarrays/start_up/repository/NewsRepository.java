package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
