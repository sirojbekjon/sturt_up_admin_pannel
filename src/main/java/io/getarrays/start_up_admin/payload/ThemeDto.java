package io.getarrays.start_up_admin.payload;

import lombok.Data;

@Data
public class ThemeDto {
    private String name;
    private float number;

    private boolean state;
    private Long partOfThemeId;
    private Long filUploadId;
}
