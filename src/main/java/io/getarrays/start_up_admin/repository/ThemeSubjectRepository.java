package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.ThemeSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeSubjectRepository extends JpaRepository<ThemeSubject, Long> {
        @Query(value =  "select * from theme_subject " +
                    "where theme_subject.subject_id = :subjectId " +
                    "and theme_subject.teacher_id = :teacherId ",
                    nativeQuery = true)
        List<ThemeSubject> getThemeSubjectBySubjectsAndTeacher(@Param("subjectId") Long subjectId,
                                                               @Param("teacherId") Long teacherId);



        @Query(value = "select * from theme_subject " +
                       "where theme_subject.subject_id = :subjectId" +
                       " order by theme_subject.number ASC ",
                        nativeQuery = true)
    List<ThemeSubject> findBySubjectId(@Param("subjectId") Long subjectId);
}



