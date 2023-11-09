package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query(value = "select * from theme" +
            " where theme.part_of_theme_id = :partOfthemeId " +
            "ORDER by theme.number ASC",
            nativeQuery = true)
    List<Theme> findByPartOfThem(@Param("partOfthemeId") Long partOfthemeId);
}
