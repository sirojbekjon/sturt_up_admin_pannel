package io.getarrays.start_up_admin.entity.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getarrays.start_up_admin.entity.Teacher;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @JsonIgnore
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    @JsonIgnore
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher createdBy;
    @JsonIgnore
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher updatedBy;

}
