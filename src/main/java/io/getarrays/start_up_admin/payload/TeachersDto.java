package io.getarrays.start_up_admin.payload;

import lombok.Data;
import java.util.List;

@Data
public class TeachersDto {

    private String name;
    private String lastname;
    private String sureName;
    private String username;
    private String phoneNumber;
    private String password;
    private String about;
    private Long role;
    private Long filePhotoId;
    private List<Long> subjects;

}
