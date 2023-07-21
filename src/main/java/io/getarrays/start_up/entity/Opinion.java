package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Opinion extends AbstractEntity {

    @Column(columnDefinition = "TEXT")
    private String discuss;

    @OneToMany
    private Teacher teacher;

    @ManyToOne
    private Subjects subjects;

}
