package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subjects,Long> {

    @Query(value = "SELECT * FROM subjects WHERE type_subject_id = :typeId",nativeQuery = true)
    List<Subjects> subjectsByTypeSubjectId(@Param("typeId") Long typeId);


}
