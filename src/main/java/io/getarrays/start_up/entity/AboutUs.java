package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import java.awt.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AboutUs extends AbstractEntity {

    private TextArea text;

}
