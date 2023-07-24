package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
