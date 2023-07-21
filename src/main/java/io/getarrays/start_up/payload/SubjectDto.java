package io.getarrays.start_up.payload;

import lombok.Data;

@Data
public class SubjectDto {

    private String name;
    private Long typeSubjectId;
    private Long teacherId;
    private String language;
    private String about;
    private Long fileUploadId;

}
