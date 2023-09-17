package io.getarrays.start_up_admin.payload;

import lombok.Data;

@Data
public class HelpsPartDto {

    private String text;
    private Long helpstypeId;
    private Long fileId;
}
