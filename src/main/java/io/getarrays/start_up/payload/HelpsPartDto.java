package io.getarrays.start_up.payload;

import lombok.Data;
import org.w3c.dom.Text;

import java.awt.*;

@Data
public class HelpsPartDto {

    private TextArea text;
    private Long helpstypeId;
    private Long fileId;
}
