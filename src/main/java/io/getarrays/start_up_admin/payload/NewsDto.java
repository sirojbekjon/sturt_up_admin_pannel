package io.getarrays.start_up_admin.payload;

import lombok.Data;

@Data
public class NewsDto {

    private String name;

    private String text;

    private Long fileUploadId;
}
