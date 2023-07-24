package io.getarrays.start_up_admin.payload;

import lombok.Data;

import java.awt.*;

@Data
public class NewsDto {

    private String name;

    private TextArea text;

    private Long fileUploadId;
}
