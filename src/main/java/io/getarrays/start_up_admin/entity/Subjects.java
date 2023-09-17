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

    private Integer views;

    private String language;

    private Integer courseCount;
    private String price;

    private Integer themeCount;

    private Integer questionCount;

    @Column(columnDefinition = "TEXT")
    private String about;

    @ManyToOne
    private FileUpload fileUpload;


    public Subjects(String name, TypeSubject typeSubject, String language, Integer courseCount, String price, Integer themeCount, Integer questionCount, String about, FileUpload fileUpload) {
        this.name = name;
        this.typeSubject = typeSubject;
        this.language = language;
        this.courseCount = courseCount;
        this.price = price;
        this.themeCount = themeCount;
        this.questionCount = questionCount;
        this.about = about;
        this.fileUpload = fileUpload;
    }
}
