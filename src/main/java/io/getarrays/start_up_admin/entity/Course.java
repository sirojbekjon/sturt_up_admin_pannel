package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class Course extends AbstractEntity {

    private String name;

    @OneToMany
    private FileUpload fileUpload;

    @OneToMany
    private ThemeSubject themeSubject;

    private final boolean status=true;//tekin yoki tekin bo'lmagan vedio darsliklarni belgilaymiz

    @Column(columnDefinition = "TEXT")
    private String additionText;      //qo'shimcha vedio uchun text yozib qo'yiladi



}
