package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ThemeSubject extends AbstractEntity {

    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Subjects subject;
    @ManyToOne
    private Teacher teacher;

}
