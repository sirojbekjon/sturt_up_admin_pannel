package io.getarrays.start_up_admin.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartOfThemeResponse {

    private Long id;
    private String name;
    private Long teacherId;


}
