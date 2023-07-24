package io.getarrays.start_up_admin.payload;

import lombok.Data;

import java.awt.*;

@Data
public class HelpsPartDto {

    private TextArea text;
    private Long helpstypeId;
    private Long fileId;
}
