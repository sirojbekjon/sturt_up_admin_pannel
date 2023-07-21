package io.getarrays.start_up.payload;

import lombok.Data;
import org.w3c.dom.Text;

import java.awt.*;

@Data
public class TeachersDto {

    private String name;
    private String lastname;
    private String surename;
    private Long filePhotoId;
    private String about;

}
