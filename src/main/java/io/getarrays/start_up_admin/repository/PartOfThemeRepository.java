package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.PartOfTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartOfThemeRepository extends JpaRepository<PartOfTheme,Long> {

    @Query(value = "SELECT * FROM part_of_theme " +
                   "where part_of_theme.theme_subject_id = :themeSubjectId " +
                   "ORDER by part_of_theme.number ASC",
                    nativeQuery = true)
    List<PartOfTheme> findByThemeSubject(@Param("themeSubjectId") Long themeSubjectId);
}
