package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subjects extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private TypeSubject typeSubject;

    @ManyToOne
    private Teacher teacher;

    private Integer views;

    private String language;

    private Integer courseCount;

    private Integer themeCount;

    private Integer questionCount;

    @Column(columnDefinition = "TEXT")
    private String about;

    @ManyToOne
    private FileUpload fileUpload;


    public Subjects(String name, TypeSubject typeSubject, Teacher teacher, String language, String about, FileUpload fileUpload) {
        this.name = name;
        this.typeSubject = typeSubject;
        this.teacher = teacher;
        this.language = language;
        this.about = about;
        this.fileUpload = fileUpload;
    }
}
