package io.getarrays.start_up_admin.payload;

import lombok.Data;

@Data
public class SubjectDto {

    private String name;
    private Long typeSubjectId;
    private String language;
    private String about;
    private Long fileUploadId;
    private Integer courseCount;
    private String price;
    private Integer themeCount;
    private Integer questionCount;

}
